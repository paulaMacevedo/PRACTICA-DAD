package es.urjc.dad.api_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.dad.api_service.messaging.NetworkProducer;
import es.urjc.dad.api_service.model.Network;
import es.urjc.dad.api_service.service.NetworkService;

@RestController
@RequestMapping("/api/networks")
public class NetworkController {

    private final NetworkProducer producer;
    private final NetworkService networkService;

    public NetworkController(NetworkProducer producer, NetworkService networkService) {
        this.producer = producer;
        this.networkService = networkService;
    }

    @PostMapping
    public ResponseEntity<String> createNetwork(@RequestBody Network network) {
        // No se guarda en la BD local, solo se reenvía la solicitud a net-service.
        producer.sendNetworkCreationRequest(network.getMask());

        return ResponseEntity.accepted().body("Network creation request sent");
    }

    @GetMapping
    public ResponseEntity<List<Network>> getAllNetworks() {
        return ResponseEntity.ok(networkService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Network> getNetworkById(@PathVariable Long id) {
        return ResponseEntity.ok(
            networkService.findById(id)
                .orElseThrow(() -> new RuntimeException("Red no encontrada con id: " + id))
        );
    }
}