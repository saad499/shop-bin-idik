package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusOrder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFullDto {
    private Long id;
    private ClientDto client;
    private List<OrderItemFullDto> orderItems;
    private Double totalPrice;
    private StatusOrder status;
    private String shippingAddress;
    private String billingAddress;
    private LocalDateTime orderDate;
}
