package org.openlan2.shop_bin_idik.mappers;

import org.openlan2.shop_bin_idik.dto.OrderDetailDto;
import org.openlan2.shop_bin_idik.dto.OrderItemDetailDto;
import org.openlan2.shop_bin_idik.entities.Order;
import org.openlan2.shop_bin_idik.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailMapper {
    public OrderDetailDto mapToOrderDetailDto(Order order) {
        List<OrderItemDetailDto> items = order.getOrderItems().stream()
                .map(this::mapToOrderItemDetailDto)
                .collect(Collectors.toList());

        return OrderDetailDto.builder()
                .numberOrder(order.getId())
                .orderDate(order.getOrderDate())
                .total(order.getTotalPrice())
                .orderStatus(order.getStatus())
                .clientNom(order.getClient().getNom())
                .clientPrenom(order.getClient().getPrenom())
                .clientTelephone(order.getClient().getTelephone())
                .clientAdresse(order.getClient().getAdresse())
                .items(items)
                .build();
    }

    public OrderItemDetailDto mapToOrderItemDetailDto(OrderItem item) {
        String imageUrl = null;
        if (item.getProduct().getImages() != null && !item.getProduct().getImages().isEmpty()) {
            imageUrl = item.getProduct().getImages().get(0).getImageUrl();
        }

        return OrderItemDetailDto.builder()
                .productName(item.getProduct().getNom())
                .productImage(imageUrl)
                .sizeName(item.getSize() != null ? item.getSize().getSizeName() : null)
                .colorName(item.getColor() != null ? item.getColor().getColorName() : null)
                .quantity(item.getQuantite())
                .unitPrice(item.getPrix())
                .totalPrice(item.getTotal())
                .build();
    }
}
