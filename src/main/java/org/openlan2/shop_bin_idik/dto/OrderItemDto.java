package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Long orderId;
    //private Long commercantId;
    private Long productId;
    private Long sizeId;
    private Long colorId;
    private Double prix;
    private Integer quantite;
    private Double total;
}
