package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.openlan2.shop_bin_idik.dto.DeliveryDto;
import org.openlan2.shop_bin_idik.entities.Delivery;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    DeliveryDto toDto(Delivery delivery);
    Delivery toEntity(DeliveryDto deliveryDto);
}
