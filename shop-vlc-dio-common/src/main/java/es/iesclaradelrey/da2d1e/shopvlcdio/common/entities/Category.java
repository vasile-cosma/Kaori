package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false,  length = 100)
    private String name;
    @Column(nullable = false,  length = 2000)
    private String description;
    @Column(length = 500)
    private String image;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

}
