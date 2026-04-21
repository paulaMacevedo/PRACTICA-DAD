package es.urjc.dad.net_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.net_service.model.Network;

public interface NetworkRepository extends JpaRepository<Network, Long> {

	Optional<Network> findByMask(String mask);
}
