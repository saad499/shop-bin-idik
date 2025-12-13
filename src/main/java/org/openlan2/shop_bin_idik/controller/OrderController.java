package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.OrderDetailDto;
import org.openlan2.shop_bin_idik.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all-details")
    public ResponseEntity<Page<OrderDetailDto>> getAllOrdersWithDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getAllOrdersWithDetails(pageable));
    }
}
