package es.urjc.dad.api_service.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.urjc.dad.api_service.model.Instance;

@Service
public class InstanceServiceClient {

    private final RestTemplate restTemplate;

    private final String instanceServiceUrl = "http://haproxy-balancer:8081/instances";

    public InstanceServiceClient() {
        this.restTemplate = new RestTemplate();
    }

    public Instance createInstance(Instance instance) {
        return restTemplate.postForObject(instanceServiceUrl, instance, Instance.class);
    }

    public List<Instance> getAllInstances() {
        ResponseEntity<List<Instance>> response = restTemplate.exchange(instanceServiceUrl,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Instance>>() {});

        return response.getBody();
    }

    public Instance getInstanceByName(String name) {
        return restTemplate.getForObject(instanceServiceUrl + "/" + name, Instance.class);
    }

    public Instance deleteInstance(String name) {
        ResponseEntity<Instance> response = restTemplate.exchange(instanceServiceUrl + "/" + name,
                HttpMethod.DELETE, null, Instance.class);
        return response.getBody();
    }

    public Instance updateInstance(String name, Instance instance) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Instance> requestEntity = new HttpEntity<>(instance, headers);

        ResponseEntity<Instance> response = restTemplate.exchange(instanceServiceUrl + "/" + name,
                HttpMethod.PUT, requestEntity, Instance.class);

        return response.getBody();
    }
}
