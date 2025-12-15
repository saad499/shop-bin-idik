package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.DeliveryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {
    Page<DeliveryDto> getAllDeliveries(Pageable pageable);
    DeliveryDto getDeliveryById(Long id);
    Page<DeliveryDto> getDeliveriesByStatus(String status, Pageable pageable);
}
