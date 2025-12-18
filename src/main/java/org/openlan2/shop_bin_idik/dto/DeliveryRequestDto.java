package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.ContactMethod;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequestDto {
    private Long deliveryId;
    private Long orderId;
    private String pickupAddress;
    private String deliveryAddress;
    private ContactMethod contactMethod;
    private Double estimatedPrice;
}
