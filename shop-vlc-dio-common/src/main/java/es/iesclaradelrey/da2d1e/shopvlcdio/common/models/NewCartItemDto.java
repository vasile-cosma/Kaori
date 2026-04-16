package es.iesclaradelrey.da2d1e.shopvlcdio.common.models;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models.ProductDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewCartItemDto {
    private Long id;
    private ProductDto product;
    private NewUserDto user;
    private Integer quantity;
    private LocalDateTime updatedAt;
}
