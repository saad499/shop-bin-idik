package org.openlan2.shop_bin_idik.controller;

import org.openlan2.shop_bin_idik.entities.Commercant;
import org.openlan2.shop_bin_idik.dto.CommercantDto;
import org.openlan2.shop_bin_idik.service.CommercantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commercants")
@CrossOrigin("*")
public class CommercantController {

    @Autowired
    private CommercantService commercantService;

    @PostMapping
    public ResponseEntity<Commercant> saveCommercant(@RequestBody Commercant commercant) {
        Commercant savedCommercant = commercantService.saveCommercant(commercant);
        return ResponseEntity.ok(savedCommercant);
    }

    @GetMapping
    public ResponseEntity<List<CommercantDto>> getAllCommercants() {
        List<CommercantDto> commercants = commercantService.getAllCommercants();
        return ResponseEntity.ok(commercants);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CommercantDto>> searchMagazinByName(@RequestParam String nameMagazin) {
        List<CommercantDto> commercants = commercantService.searchMagazinByName(nameMagazin);
        return ResponseEntity.ok(commercants);
    }
}
