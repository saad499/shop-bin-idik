package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openlan2.shop_bin_idik.dto.ProductDto;
import org.openlan2.shop_bin_idik.dto.ProductFullDto;
import org.openlan2.shop_bin_idik.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categorieId", source = "categorie.id")
    ProductDto toDto(Product product);

    @Mapping(target = "categorie", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "isActiveProduct", ignore = true)
    Product toEntity(ProductDto dto);
    
    @Mapping(target = "categorie", ignore = true)
    ProductFullDto toFullDto(Product product);
}
