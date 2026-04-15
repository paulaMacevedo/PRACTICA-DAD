package es.urjc.dad.api_service.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.dad.api_service.model.Network;
import es.urjc.dad.api_service.service.NetworkService;

@RestController
@RequestMapping("/networks")
public class NetworkController {
    private final NetworkService networkService;

    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }
    @PostMapping
public Network createNetwork(@RequestBody Network network) {
    return networkService.save(network);
}
@GetMapping
public List<Network> getAllNetworks() {
    return networkService.findAll();
}

@GetMapping("/{id}")
public Network getNetworkById(@PathVariable Long id) {
    return networkService.findById(id).orElse(null);
}

@DeleteMapping("/{id}")
public void deleteNetwork(@PathVariable Long id) {
    networkService.deleteNetwork(id);
}
}
