package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.ProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class NewCartItemDto {
    private Long id;
    private NewProductDto product;
    private NewUserDto user;
    private Integer quantity;
    private LocalDateTime updatedAt;
}
