package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.openlan2.shop_bin_idik.dto.ColorDto;
import org.openlan2.shop_bin_idik.entities.Color;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    ColorDto toDto(Color color);

    Color toEntity(ColorDto dto);
}