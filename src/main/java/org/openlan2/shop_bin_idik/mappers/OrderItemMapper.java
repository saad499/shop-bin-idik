package org.openlan2.shop_bin_idik.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openlan2.shop_bin_idik.dto.OrderItemDto;
import org.openlan2.shop_bin_idik.dto.OrderItemFullDto;
import org.openlan2.shop_bin_idik.entities.OrderItem;

@Mapper(componentModel = "spring",
        uses = {ProductMapper.class, SizeMapper.class, ColorMapper.class})
public interface OrderItemMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "size.id", target = "sizeId")
    @Mapping(source = "color.id", target = "colorId")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "sizeId", target = "size.id")
    @Mapping(source = "colorId", target = "color.id")
    OrderItem toEntity(OrderItemDto dto);

    @Mapping(source = "product", target = "product")
    @Mapping(source = "size", target = "size")
    @Mapping(source = "color", target = "color")
    OrderItemFullDto toFullDto(OrderItem orderItem);
}
