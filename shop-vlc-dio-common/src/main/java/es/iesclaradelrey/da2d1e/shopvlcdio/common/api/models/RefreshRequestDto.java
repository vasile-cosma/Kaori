package es.iesclaradelrey.da2d1e.shopvlcdio.common.api.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshRequestDto {
    private String refreshToken;
}
