package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;


import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Entity;

import java.util.List;
import java.util.Optional;


public interface Repository<ID, T extends Entity<ID>> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T item);
    void delete(T item);
}
