package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.openlan2.shop_bin_idik.constant.RequestStatus;
import org.openlan2.shop_bin_idik.dto.DeliveryDto;
import org.openlan2.shop_bin_idik.dto.DeliveryRequestDto;
import org.openlan2.shop_bin_idik.dto.DeliveryRequestResponseDto;
import org.openlan2.shop_bin_idik.dto.OrderClientInfoDto;
import org.openlan2.shop_bin_idik.dto.DeliveryRequestInfoDto;
import org.openlan2.shop_bin_idik.entities.Client;
import org.openlan2.shop_bin_idik.entities.Delivery;
import org.openlan2.shop_bin_idik.entities.DeliveryRequest;
import org.openlan2.shop_bin_idik.entities.Order;
import org.openlan2.shop_bin_idik.mappers.DeliveryMapper;
import org.openlan2.shop_bin_idik.repository.DeliveryRepository;
import org.openlan2.shop_bin_idik.repository.DeliveryRequestRepository;
import org.openlan2.shop_bin_idik.repository.OrderRepository;
import org.openlan2.shop_bin_idik.service.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final DeliveryRequestRepository deliveryRequestRepository;
    private final OrderRepository orderRepository;

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
    @Override
    @Transactional
    public DeliveryRequestResponseDto sendDeliveryRequest(DeliveryRequestDto requestDto) {
        Delivery delivery = deliveryRepository.findById(requestDto.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + requestDto.getDeliveryId()));

        Order order = null;
        if (requestDto.getOrderId() != null) {
            order = orderRepository.findById(requestDto.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Order not found with id: " + requestDto.getOrderId()));
        }

        DeliveryRequest deliveryRequest = DeliveryRequest.builder()
                .delivery(delivery)
                .order(order)
                .pickupAddress(requestDto.getPickupAddress())
                .deliveryAddress(requestDto.getDeliveryAddress())
                .contactMethod(requestDto.getContactMethod())
                .estimatedPrice(requestDto.getEstimatedPrice())
                .status(RequestStatus.PENDING)
                .message("Request sent successfully")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        DeliveryRequest savedRequest = deliveryRequestRepository.save(deliveryRequest);

        return DeliveryRequestResponseDto.builder()
                .requestId(savedRequest.getId())
                .status(savedRequest.getStatus())
                .deliveryId(savedRequest.getDelivery().getId())
                .message(savedRequest.getMessage())
                .build();
    }

    @Override
    public DeliveryRequestResponseDto checkRequestStatus(Long requestId) {
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Delivery request not found with id: " + requestId));

        return DeliveryRequestResponseDto.builder()
                .requestId(deliveryRequest.getId())
                .status(deliveryRequest.getStatus())
                .deliveryId(deliveryRequest.getDelivery().getId())
                .message(deliveryRequest.getMessage())
                .build();
    }

    @Override
    public List<OrderClientInfoDto> findByStatus() {
        List<Order> orders = orderRepository.findByStatusEnTraitement();
        return orders.stream()
                .map(order -> {
                    Client client = order.getClient();
                    return new OrderClientInfoDto(
                        order.getId(),
                        client.getPrenom(),
                        client.getNom(),
                        order.getTotalPrice()
                    );
                })
                .toList();
    }

    @Override
    public DeliveryRequest getDeliveryRequestByOrderAndDelivery(Long orderId, Long deliveryId) {
        return deliveryRequestRepository.findByOrderIdAndDeliveryId(orderId, deliveryId)
                .orElseThrow(() -> new RuntimeException("DeliveryRequest not found for orderId " + orderId + " and deliveryId " + deliveryId));
    }

    @Override
    public DeliveryRequestInfoDto getDeliveryInfo(Long orderId, Long deliveryId) {
        DeliveryRequest deliveryRequest = deliveryRequestRepository.findByOrderIdAndDeliveryId(orderId, deliveryId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "DeliveryRequest not found for orderId " + orderId + " and deliveryId " + deliveryId
                ));
        return new DeliveryRequestInfoDto(
            deliveryRequest.getPickupAddress(),
            deliveryRequest.getDeliveryAddress(),
            deliveryRequest.getContactMethod().name(),
            deliveryRequest.getEstimatedPrice()
        );
    }
}
