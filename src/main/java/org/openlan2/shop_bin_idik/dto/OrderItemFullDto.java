package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemFullDto {
    private Long id;
    private ProductDto product;
    private SizeDto size;
    private ColorDto color;
    private Double prix;
    private Integer quantite;
    private Double total;
}
