package org.openlan2.shop_bin_idik.dto;

import lombok.Data;
import org.openlan2.shop_bin_idik.constant.StatusProduct;

import java.util.List;

@Data
public class ProductDto {
    private String nom;
    private String description;
    private Double prix;
    private List<String> sizes;
    private Integer stock;
    private StatusProduct status;
    private Long categorieId;
}
