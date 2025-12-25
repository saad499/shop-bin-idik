package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.CommercantDto;
import org.openlan2.shop_bin_idik.service.CommercantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commercants")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommercantController {
    
    private final CommercantService commercantService;

    @GetMapping
    public ResponseEntity<List<CommercantDto>> getAllCommercants() {
        return ResponseEntity.ok(commercantService.getAllCommercants());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CommercantDto>> searchMagazinByName(@RequestParam String nameMagazin) {
        return ResponseEntity.ok(commercantService.searchMagazinByName(nameMagazin));
    }
}
