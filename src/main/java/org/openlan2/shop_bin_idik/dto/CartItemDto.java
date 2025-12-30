package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {
    private Long id;
    private String productName;
    private String productImage;
    private String colorName;
    private String sizeName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
