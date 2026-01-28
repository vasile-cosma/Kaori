package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { }
