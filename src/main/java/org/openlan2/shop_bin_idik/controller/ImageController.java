package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ImageController {

    private final ImageService imageService;

    @DeleteMapping
    public ResponseEntity<Void> deleteImage(@RequestParam Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
