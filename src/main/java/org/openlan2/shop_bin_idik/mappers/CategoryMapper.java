package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.openlan2.shop_bin_idik.dto.CategoryDto;
import org.openlan2.shop_bin_idik.dto.CategoryFullDto;
import org.openlan2.shop_bin_idik.entities.Categorie;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Categorie categorie);
    Categorie toEntity(CategoryDto dto);
    CategoryFullDto toFullDto(Categorie categorie);
}
