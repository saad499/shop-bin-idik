package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.DeliveryDto;
import org.openlan2.shop_bin_idik.service.DeliveryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping
    public ResponseEntity<Page<DeliveryDto>> getAllDeliveries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(deliveryService.getAllDeliveries(pageable));
    }

    @GetMapping("/one")
    public ResponseEntity<DeliveryDto> getDeliveryById(@RequestParam Long id) {
        return ResponseEntity.ok(deliveryService.getDeliveryById(id));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<DeliveryDto>> getDeliveriesByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(deliveryService.getDeliveriesByStatus(status, pageable));
    }
}
