package es.urjc.dad.api_service.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.urjc.dad.api_service.model.Network;

@Service
public class NetworkServiceClient {
        private final RestTemplate restTemplate;

    public NetworkServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<Network> getAllNetworks() {
        Network[] networks = restTemplate.getForObject(
            "http://localhost:8082/networks", 
            Network[].class
        );
        return Arrays.asList(networks);
    }

    public Network getNetworkByName(String name) {
        return restTemplate.getForObject(
            "http://localhost:8082/networks/" + name,
            Network.class
        );
    }
}
