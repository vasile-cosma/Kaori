package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.SecurityEvent;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.enums.SecurityEventType;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface SecurityEventService {
    List<SecurityEvent> findAll();

    List<SecurityEvent> findByType(SecurityEventType securityEventType);

    List<SecurityEvent> findByUsername(String username);

    Optional<SecurityEvent> findById(@PathVariable Long id);

    SecurityEvent save(SecurityEvent event);

}
