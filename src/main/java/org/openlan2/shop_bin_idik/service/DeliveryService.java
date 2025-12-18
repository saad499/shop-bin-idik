package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.DeliveryDto;
import org.openlan2.shop_bin_idik.dto.DeliveryRequestDto;
import org.openlan2.shop_bin_idik.dto.DeliveryRequestResponseDto;
import org.openlan2.shop_bin_idik.dto.OrderClientInfoDto;
import org.openlan2.shop_bin_idik.dto.DeliveryRequestInfoDto;
import org.openlan2.shop_bin_idik.entities.DeliveryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeliveryService {
    Page<DeliveryDto> getAllDeliveries(Pageable pageable);
    DeliveryDto getDeliveryById(Long id);
    Page<DeliveryDto> getDeliveriesByStatus(String status, Pageable pageable);
    DeliveryRequestResponseDto sendDeliveryRequest(DeliveryRequestDto requestDto);
    DeliveryRequestResponseDto checkRequestStatus(Long requestId);
    List<OrderClientInfoDto> findByStatus();
    DeliveryRequest getDeliveryRequestByOrderAndDelivery(Long orderId, Long deliveryId);
    DeliveryRequestInfoDto getDeliveryInfo(Long orderId, Long deliveryId);
}
