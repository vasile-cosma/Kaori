package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(unique = true, nullable = false, length = 200)
    private String email;
    @Column(unique = true, length = 200)
    private String phoneNumber;
    @Column(length = 200)
    private LocalDate birthDate;
    @Column(nullable = false, length = 25)
    private String password;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(unique = true, nullable = false, length = 200)
    private String username;




}
