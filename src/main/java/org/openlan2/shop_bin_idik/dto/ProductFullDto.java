package org.openlan2.shop_bin_idik.dto;

import lombok.Data;
import org.openlan2.shop_bin_idik.constant.StatusProduct;

import java.time.LocalDateTime;

@Data
public class ProductFullDto {
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private StatusProduct status;
    private Boolean isActiveProduct;
    private LocalDateTime dateCreated;
    private CategoryDto categorie;
}
