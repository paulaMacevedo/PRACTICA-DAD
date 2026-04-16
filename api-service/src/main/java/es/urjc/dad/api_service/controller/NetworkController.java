package es.urjc.dad.api_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.dad.api_service.messaging.NetworkProducer;
import es.urjc.dad.api_service.model.Network;

@RestController
@RequestMapping("/networks")
public class NetworkController {

    private final NetworkProducer producer;

    public NetworkController(NetworkProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> createNetwork(@RequestBody Network network) {

        producer.sendNetworkCreationRequest(network.getMask());

        return ResponseEntity.accepted().body("Network creation request sent");
    }
}