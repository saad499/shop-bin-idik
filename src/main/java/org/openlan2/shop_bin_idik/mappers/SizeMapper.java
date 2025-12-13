package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.openlan2.shop_bin_idik.dto.SizeDto;
import org.openlan2.shop_bin_idik.entities.Size;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    SizeDto toDto(Size size);

    Size toEntity(SizeDto dto);
}
