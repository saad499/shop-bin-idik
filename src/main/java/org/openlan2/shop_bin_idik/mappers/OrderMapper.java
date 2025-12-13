package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openlan2.shop_bin_idik.dto.OrderDto;
import org.openlan2.shop_bin_idik.dto.OrderFullDto;
import org.openlan2.shop_bin_idik.entities.Order;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, ClientMapper.class})
public interface OrderMapper {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderDto toDto(Order order);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "orderItems", target = "orderItems")
    Order toEntity(OrderDto dto);

    @Mapping(source = "client", target = "client")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderFullDto toFullDto(Order order);
}
