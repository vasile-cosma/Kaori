package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.ProductDto;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.AppUser;
import es.iesclaradelrey.da2d1e.shopvlcdio.common.entities.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCartItemDto {
    private ProductDto product;
    private Integer units;
}
