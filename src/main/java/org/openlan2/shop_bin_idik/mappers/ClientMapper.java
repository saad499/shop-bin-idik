package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openlan2.shop_bin_idik.dto.ClientDto;
import org.openlan2.shop_bin_idik.entities.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(source = "user.id", target = "userId")
    ClientDto toDto(Client client);

    @Mapping(source = "userId", target = "user.id")
    Client toEntity(ClientDto dto);
}
