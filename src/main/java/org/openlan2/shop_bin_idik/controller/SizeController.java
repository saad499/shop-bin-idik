package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SizeController {

    private final SizeService sizeService;

    @DeleteMapping
    public ResponseEntity<Void> deleteSize(@RequestParam Long id) {
        sizeService.deleteSize(id);
        return ResponseEntity.noContent().build();
    }
}
