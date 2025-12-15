package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDto {
    private Long id;
    private String nomComplet;
    private Double note;
    private String typeVehicule;
    private String delai;
    private String status;
}
