package es.urjc.dad.api_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.dad.api_service.model.Instance;
import es.urjc.dad.api_service.service.InstanceServiceClient;

@RestController
@RequestMapping("/api/instances")
public class InstanceController {
    private final InstanceServiceClient instanceServiceClient;

    public InstanceController(InstanceServiceClient instanceServiceClient) {
        this.instanceServiceClient = instanceServiceClient;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Instance> createInstance(@RequestBody Instance instance) {
        Instance createdInstance = instanceServiceClient.createInstance(instance);
        return ResponseEntity.ok(createdInstance);
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Instance>> getAllInstances() {
        return ResponseEntity.ok(instanceServiceClient.getAllInstances());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Instance> getInstanceByName(@PathVariable String name) {
        return ResponseEntity.ok(instanceServiceClient.getInstanceByName(name));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Instance> updateInstance(@PathVariable String name,
            @RequestBody Instance instance) {
        if (name == null || name.isBlank()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(instanceServiceClient.updateInstance(name, instance));
        }
    }


    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteInstance(@PathVariable String name) {
        try {
            Instance deletedInstance = instanceServiceClient.deleteInstance(name);
            if (deletedInstance == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(deletedInstance);
            }
        } catch (org.springframework.web.client.HttpStatusCodeException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                    .body(e.getResponseBodyAsString());
        }
    }

}
