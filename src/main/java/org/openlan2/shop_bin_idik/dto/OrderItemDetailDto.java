package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDetailDto {
    private String productName;//
    private String productDescription;//
    private String productImage;//
    private Integer disponibilite;//
    private String sizeName;//
    private String colorName;//
    private String colorCode;//
    private Double prix;
    private Integer quantite;
    private Double total;
}
