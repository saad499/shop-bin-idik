package org.openlan2.shop_bin_idik.controller;

import org.openlan2.shop_bin_idik.entities.Commercant;
import org.openlan2.shop_bin_idik.service.CommercantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commercants")
public class CommercantController {

    @Autowired
    private CommercantService commercantService;

    @PostMapping
    public ResponseEntity<Commercant> saveCommercant(@RequestBody Commercant commercant) {
        Commercant savedCommercant = commercantService.saveCommercant(commercant);
        return ResponseEntity.ok(savedCommercant);
    }
}
