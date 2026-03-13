package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class AppRole {
    @Id
    @Column(length = 6)
    private String id;
    @Column(length = 100)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users = new HashSet<>();
}
