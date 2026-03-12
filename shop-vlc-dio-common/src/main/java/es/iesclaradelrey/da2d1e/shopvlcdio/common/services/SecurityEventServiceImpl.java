package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.SecurityEvent;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.enums.SecurityEventType;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.SecurityEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SecurityEventServiceImpl implements SecurityEventService {
    private final SecurityEventRepository securityEventRepository;

    public SecurityEventServiceImpl(SecurityEventRepository securityEventRepository) {
        this.securityEventRepository = securityEventRepository;
    }

    @Override
    public List<SecurityEvent> findAll() {
        return securityEventRepository.findAll();
    }

    @Override
    public Optional<SecurityEvent> findById(Long id) {
        return securityEventRepository.findById(id);
    }

    @Override
    public SecurityEvent save(SecurityEvent event) {
        return securityEventRepository.save(event);
    }

    @Override
    public List<SecurityEvent> findByType(SecurityEventType securityEventType) {
        return securityEventRepository.findByType(securityEventType);
    }

    @Override
    public List<SecurityEvent> findByUsername(String username) {
        return securityEventRepository.findByUsername(username);
    }

}
