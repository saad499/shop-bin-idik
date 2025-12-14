package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusOrder;
import org.openlan2.shop_bin_idik.dto.OrderDetailDto;
import org.openlan2.shop_bin_idik.entities.Order;
import org.openlan2.shop_bin_idik.mappers.OrderDetailMapper;
import org.openlan2.shop_bin_idik.repository.OrderRepository;
import org.openlan2.shop_bin_idik.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailMapper mapper;

    @Override
    public Page<OrderDetailDto> getAllOrdersWithDetails(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(mapper::mapToOrderDetailDto);
    }

    @Override
    public List<OrderDetailDto> getAllOrdersWithDetails() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(mapper::mapToOrderDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDetailDto> getOrdersByStatus(StatusOrder status, Pageable pageable) {
        Page<Order> orders = orderRepository.findByStatus(status, pageable);
        return orders.map(mapper::mapToOrderDetailDto);
    }
}
