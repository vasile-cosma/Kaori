package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
        private String accessToken;
        private String refreshToken;
}
