package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryRequestInfoDto {
    private String pickupAddress;
    private String deliveryAddress;
    private String contactMethod;
    private Double estimatedPrice;
}
