package es.iesclaradelrey.da2d1e.shopvlcdio.common.entities;

import es.iesclaradelrey.da2d1e.shopvlcdio.common.enums.SecurityEventType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private SecurityEventType type;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationDate;
}
