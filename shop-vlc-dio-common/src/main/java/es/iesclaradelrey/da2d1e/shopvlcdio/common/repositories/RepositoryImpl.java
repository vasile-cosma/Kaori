package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Entity;

import java.util.*;

public class RepositoryImpl<ID, T extends Entity<ID>> implements Repository<ID, T> {
    private final Map<ID, T> items = new HashMap<>();

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(items.get(id));
    }

    @Override
    public T save(T item) {
        if (item.getID() == null) {
            throw new IllegalArgumentException("ID is null");
        }
        return items.putIfAbsent(item.getID(), item);
    }

    @Override
    public void delete(T item) {
        if (!item.containsKey(item.getID())){
            throw new NoSuchElementException(String.format("No se enceuntra el elemento con id %s.\n", item.getID()));
        }
    }
}
