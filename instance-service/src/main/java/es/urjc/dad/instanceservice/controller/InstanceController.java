package es.urjc.dad.instanceservice.controller;

import es.urjc.dad.instanceservice.model.Instance;
import es.urjc.dad.instanceservice.repository.InstanceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instances") // All the URL's start with /instances

public class InstanceController {

    private final InstanceRepository repository;

    // Dependecy injection of the repository
    public InstanceController(InstanceRepository repository) {
        this.repository = repository;
    }

    // Get all the instances
    @GetMapping
    public List<Instance> getAllInstances() {
        return repository.findAll();
    }

    // Get an instance by name
    @GetMapping("/{name}")
    public ResponseEntity<Instance> getInstanceByName(@PathVariable String name) {
        Optional<Instance> instance = repository.findByName(name);
        if (instance.isPresent()) {
            return ResponseEntity.ok(instance.get());
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }

    }
}
