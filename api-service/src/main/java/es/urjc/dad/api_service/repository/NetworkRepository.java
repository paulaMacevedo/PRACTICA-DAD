package es.urjc.dad.api_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.api_service.model.Network;

public interface NetworkRepository extends JpaRepository<Network, Long> {
    // It is not defined the methods because of Spring, which is going to generate automaticaly
}