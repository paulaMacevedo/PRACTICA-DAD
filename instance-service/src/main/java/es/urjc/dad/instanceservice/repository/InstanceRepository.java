package es.urjc.dad.instanceservice.repository;

import es.urjc.dad.instanceservice.model.Instance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InstanceRepository extends JpaRepository<Instance, Long> {


    Optional<Instance> findByName(String name);

    boolean existsByDependsOnAndStatusNot(String dependsOn, String status);
}
