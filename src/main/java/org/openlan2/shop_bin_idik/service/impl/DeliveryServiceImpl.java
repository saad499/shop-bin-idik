package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.DeliveryDto;
import org.openlan2.shop_bin_idik.entities.Delivery;
import org.openlan2.shop_bin_idik.mappers.DeliveryMapper;
import org.openlan2.shop_bin_idik.repository.DeliveryRepository;
import org.openlan2.shop_bin_idik.service.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Override
    public Page<DeliveryDto> getAllDeliveries(Pageable pageable) {
        return deliveryRepository.findAll(pageable)
                .map(deliveryMapper::toDto);
    }

    @Override
    public DeliveryDto getDeliveryById(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
        return deliveryMapper.toDto(delivery);
    }

    @Override
    public Page<DeliveryDto> getDeliveriesByStatus(String status, Pageable pageable) {
        return deliveryRepository.findByStatus(status, pageable)
                .map(deliveryMapper::toDto);
    }
}
