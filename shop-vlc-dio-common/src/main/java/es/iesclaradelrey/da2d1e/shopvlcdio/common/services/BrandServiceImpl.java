package es.iesclaradelrey.da2d1e.shopvlcdio.common.services;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Brand;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.services.mappers.BrandMapper;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.models.NewBrandDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }


    public Optional<Brand> findById(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand item) {
        return brandRepository.save(item);
    }

    @Override
    public void delete(Brand item) {
        brandRepository.delete(item);
    }

    @Override
    public Brand createNew(NewBrandDto newBrandDto) {
        Brand brand = BrandMapper.map(newBrandDto);

        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Integer brandId, NewBrandDto newBrandDto) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new EntityNotFoundException(String.format("No se encuentra la marcan con id %d", brandId)));
        if (!newBrandDto.getName().isEmpty()){
            brand.setName(newBrandDto.getName());
        }
        return brandRepository.save(brand);
    }
}
