package org.openlan2.shop_bin_idik.dto;

import lombok.Data;

@Data
public class CategoryFullDto {
    private String nom;
    private String description;
    private Boolean isActiveCategory;
}
