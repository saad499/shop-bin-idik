package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.SendReclamationRequest;
import org.openlan2.shop_bin_idik.dto.SupportDto;
import org.openlan2.shop_bin_idik.dto.SupportRequestDto;
import org.openlan2.shop_bin_idik.entities.Support;
import org.openlan2.shop_bin_idik.service.SupportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/support")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SupportController {
    
    private final SupportService supportService;

    @PostMapping("/save")
    public ResponseEntity<SupportDto> saveSupport(@RequestBody SendReclamationRequest request) {
        SupportDto savedSupport = supportService.sendReclamation(request);
        return ResponseEntity.ok(savedSupport);
    }
}
