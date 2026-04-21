package es.urjc.dad.net_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.dad.net_service.model.IpAssignment;

public interface IpAssignmentRepository extends JpaRepository<IpAssignment, Long> {

	Optional<IpAssignment> findByServerName(String serverName);

	List<IpAssignment> findByNetworkId(Long networkId);
}
