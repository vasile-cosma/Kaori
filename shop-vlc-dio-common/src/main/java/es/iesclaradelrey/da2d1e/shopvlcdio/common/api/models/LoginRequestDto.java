package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {
        private String username;
        private String password;
}
