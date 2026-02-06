package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private String code;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 4000, nullable = false)
    private String desc;
    @Column(length = 500)
    private String img;
    @Column(nullable = false)
    private Double price;
    //Todo Añadir validación
    @Column(nullable = false, length = 99)
    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Double calculatePrice() {
        Double productDiscount = price * discount/100;
        return price - productDiscount;
    }
}
