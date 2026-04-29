package es.urjc.dad.instanceservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.dad.instanceservice.model.Instance;
import es.urjc.dad.instanceservice.repository.InstanceRepository;
import es.urjc.dad.instanceservice.messaging.InstanceMessageProducer;

@RestController
@RequestMapping("/instances") // All the URL's start with /instances

public class InstanceController {

    private InstanceRepository repository;
    private InstanceMessageProducer messageProducer;


    public InstanceController(InstanceRepository repository,
            InstanceMessageProducer messageProducer) {
        this.repository = repository;
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public ResponseEntity<?> createInstance(@RequestBody Instance instance) {
        // Check if the instance name is already taken
        if (repository.findByName(instance.getName()).isPresent()) {
            return ResponseEntity.status(409)
                    .body("Instance with name " + instance.getName() + " already exists."); // Return
                                                                                            // 409
                                                                                            // Conflict
                                                                                            // if
                                                                                            // name
                                                                                            // is
                                                                                            // taken
        }


        instance.setIp(null);
        instance.setStatus("PENDING");

        Instance savedInstance = repository.save(instance);

        messageProducer.requestIpAssignment(savedInstance.getName(), savedInstance.getMask());


        return ResponseEntity.ok(savedInstance);
    }

    @GetMapping
    public List<Instance> getAllInstances() {
        return repository.findAll().stream()
                .filter(instance -> !"DELETED".equals(instance.getStatus())) // Exclude deleted
                                                                             // instances
                .toList();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Instance> getInstance(@PathVariable String name) {

        Optional<Instance> instance = repository.findByName(name);
        if (instance.isPresent() && !"DELETED".equals(instance.get().getStatus())) {
            return ResponseEntity.ok(instance.get());
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteInstance(@PathVariable String name) {
        Optional<Instance> optionalInstance = repository.findByName(name);

        if (optionalInstance.isPresent()) {
            Instance instance = optionalInstance.get();

            if (repository.existsByDependsOnAndStatusNot(instance.getName(), "DELETED")) {
                return ResponseEntity.status(409)
                        .body("Cannot delete instance because other instances depend on it.");
            }
            instance.setStatus("DELETED");

            messageProducer.requestIpUnassignment(instance.getName());

            Instance savedInstance = repository.save(instance);
            return ResponseEntity.ok(savedInstance);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<Instance> updateInstance(@PathVariable String name,
            @RequestBody Instance updated) {
        Optional<Instance> existingInstance = repository.findByName(name);

        if (existingInstance.isPresent()) {
            Instance instanceToUpdate = existingInstance.get();

            // Update the fields of the existing instance with the values from the updated instance
            instanceToUpdate.setCpu(updated.getCpu());
            instanceToUpdate.setMemory(updated.getMemory());
            instanceToUpdate.setDisk(updated.getDisk());

            instanceToUpdate.setStatus("RESTARTING");
            instanceToUpdate.setIp(null);

            Instance saved = repository.save(instanceToUpdate);

            messageProducer.requestIpUnassignment(saved.getName());

            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }

}

