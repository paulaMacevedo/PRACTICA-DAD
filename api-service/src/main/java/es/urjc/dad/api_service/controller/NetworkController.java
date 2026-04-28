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
import es.urjc.dad.api_service.service.NetworkServiceClient;

@RestController
@RequestMapping("/networks")
public class NetworkController {

    private final NetworkProducer producer;
    private final NetworkServiceClient client;

    public NetworkController(NetworkProducer producer, NetworkServiceClient client) {
        this.producer = producer;
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<String> createNetwork(@RequestBody Network network) {

        producer.sendNetworkCreationRequest(network.getMask());

        return ResponseEntity.accepted().body("Network creation request sent");
    }
     @GetMapping
    public ResponseEntity<List<Network>> getAllInstances() {
        return ResponseEntity.ok(client.getAllNetworks());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Network> getInstanceByName(@PathVariable String name) {
        return ResponseEntity.ok(client.getNetworkByName(name));
    }
}