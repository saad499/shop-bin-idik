package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.RequestStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequestResponseDto {
    private Long requestId;
    private RequestStatus status;
    private Long deliveryId;
    private String message;
}
