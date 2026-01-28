package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 13)
    //Todo Añadir validación
    private String productCode;
    @Column(length = 200, nullable = false)
    private String productName;
    @Column(length = 50)
    private String productBrand;
    @Column(length = 4000, nullable = false)
    private String productDescription;
    @Column(length = 500)
    private String productImage;
    @Column(nullable = false)
    private Double productPrice;
    //Todo Añadir validación
    @Column(nullable = false, length = 99)
    private Integer productDiscount;

    public Double calculatePrice() {
        return productPrice * productDiscount/100;
    }
}
