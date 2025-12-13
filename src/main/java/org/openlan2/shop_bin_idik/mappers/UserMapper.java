package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.openlan2.shop_bin_idik.dto.UserDto;
import org.openlan2.shop_bin_idik.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
