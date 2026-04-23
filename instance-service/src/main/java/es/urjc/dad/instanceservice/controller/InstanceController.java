package es.urjc.dad.instanceservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.dad.instanceservice.model.Instance;

@RestController
@RequestMapping("/instances") // All the URL's start with /instances

public class InstanceController {

    private static List<Instance> instances = new ArrayList<>();

    @PostMapping
    public Instance createInstance(@RequestBody Instance instance) {
        instances.add(instance);
        return instance;
    }

    @GetMapping("/{name}")
    public Instance getInstance(@PathVariable String name) {
        return instances.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    
    @DeleteMapping("/{name}")
    public void deleteInstance(@PathVariable String name) {
    instances.removeIf(i -> i.getName().equals(name));
    }

  @PutMapping("/{name}")
    public Instance updateInstance(@PathVariable String name, @RequestBody Instance updated) {
     for (int i = 0; i < instances.size(); i++) {
        if (instances.get(i).getName().equals(name)) {
            instances.set(i, updated); 
            return instances.get(i);
        }
    }
    return null;
}
}
