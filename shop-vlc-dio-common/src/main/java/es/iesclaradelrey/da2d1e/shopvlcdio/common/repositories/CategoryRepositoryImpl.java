package es.iesclaradelrey.da2d1e.shopvlcdio.common.repositories;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl extends RepositoryImpl<Long, Category> implements CategoryRepository { }

