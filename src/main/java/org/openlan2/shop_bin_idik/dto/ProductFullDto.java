package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.openlan2.shop_bin_idik.constant.StatusProduct;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFullDto {
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private List<SizeDto> sizes;
    private List<ColorDto> colors;
    private List<ImageDto> images;
    private Integer stock;
    private StatusProduct status;
    private Boolean isActiveProduct;
    private LocalDateTime dateCreated;
    private CategoryDto categorie;
}
