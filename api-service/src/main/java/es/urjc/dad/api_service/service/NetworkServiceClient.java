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

    // 🔹 GET ALL
    public List<Network> getAllNetworks() {
        Network[] networks =
                restTemplate.getForObject("http://net-service-1:8080/networks/", Network[].class);
        return Arrays.asList(networks);
    }

    // 🔹 GET BY ID (IMPORTANTE)
    public Network getNetworkById(Long id) {
        return restTemplate.getForObject("http://net-service-1:8080/networks/" + id, Network.class);
    }
}
