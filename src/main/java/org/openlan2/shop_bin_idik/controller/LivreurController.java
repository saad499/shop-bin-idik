package org.openlan2.shop_bin_idik.controller;

import org.openlan2.shop_bin_idik.entities.Livreur;
import org.openlan2.shop_bin_idik.service.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livreurs")
@CrossOrigin("*")
public class LivreurController {

    @Autowired
    private LivreurService livreurService;

    @PostMapping
    public ResponseEntity<Livreur> saveLivreur(@RequestBody Livreur livreur) {
        Livreur savedLivreur = livreurService.saveLivreur(livreur);
        return ResponseEntity.ok(savedLivreur);
    }
}
