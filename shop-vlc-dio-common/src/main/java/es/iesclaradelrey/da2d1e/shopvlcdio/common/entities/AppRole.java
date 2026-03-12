package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class AppRole {
    @Id
    @Column(length = 6)
    private String id;
    @Column(length = 100)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users = new HashSet<>();
}
