package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface BrandService{
    List<Brand> findAll();

    Optional<Brand> findById(@PathVariable Integer id);

    Brand save(Brand item);

    void delete(Brand item);

    Brand createNew(NewBrandDto newBrandDto);
}
