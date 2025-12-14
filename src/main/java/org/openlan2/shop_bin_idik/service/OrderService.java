package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.constant.StatusOrder;
import org.openlan2.shop_bin_idik.dto.OrderDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Page<OrderDetailDto> getAllOrdersWithDetails(Pageable pageable);
    List<OrderDetailDto> getAllOrdersWithDetails();
    Page<OrderDetailDto> getOrdersByStatus(StatusOrder status, Pageable pageable);
}
