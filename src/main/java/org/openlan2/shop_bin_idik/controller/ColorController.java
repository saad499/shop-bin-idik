package org.openlan2.shop_bin_idik.controller;
import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.service.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/colors")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ColorController {

    private final ColorService colorService;

    @DeleteMapping
    public ResponseEntity<Void> deleteColor(@RequestParam Long id) {
        colorService.deleteColor(id);
        return ResponseEntity.noContent().build();
    }
}
