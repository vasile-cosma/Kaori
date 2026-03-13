package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.SecurityEvent;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.enums.SecurityEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityEventRepository extends JpaRepository<SecurityEvent, Long> {
    List<SecurityEvent> findByUsername(String username);
    List<SecurityEvent> findByType(SecurityEventType securityEventType);
}
