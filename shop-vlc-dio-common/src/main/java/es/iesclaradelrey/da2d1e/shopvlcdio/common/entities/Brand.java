package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, length = 150)
    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<Product> products = new HashSet<>();

}
