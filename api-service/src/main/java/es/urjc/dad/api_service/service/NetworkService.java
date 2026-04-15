package es.urjc.dad.api_service.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.urjc.dad.api_service.model.Network;
import es.urjc.dad.api_service.repository.NetworkRepository;

@Service
public class NetworkService {

    private final NetworkRepository repository;

    public NetworkService(NetworkRepository repository) {
        this.repository = repository;
    }

    public Network save(Network network) {
        return repository.save(network);
    }

    public List<Network> findAll() {
        return repository.findAll();
    }

    public Optional<Network> findById(Long id) {
        return repository.findById(id);
    }
    
    public void deleteNetwork(Long id) {
        repository.deleteById(id);
    }
}