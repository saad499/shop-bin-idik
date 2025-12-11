package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.CategoryDto;
import org.openlan2.shop_bin_idik.dto.CategoryFullDto;
import org.openlan2.shop_bin_idik.entities.Categorie;
import org.openlan2.shop_bin_idik.service.CategorieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategorieController {

    private final CategorieService categorieService;

    @PostMapping
    public ResponseEntity<Categorie> create(@RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categorieService.createCategory(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<Categorie> update(@RequestParam Long id, @RequestBody CategoryDto dto) {
        return ResponseEntity.ok(categorieService.updateCategory(id, dto));
    }

    @PostMapping("/deactivate")
    public ResponseEntity<Categorie> deactivateCategory(@RequestParam Long id) {
        Categorie updated = categorieService.setIsActiveCategoryFalse(id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        categorieService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<CategoryDto>> getAllByIsActive(@RequestParam boolean isActive) {
        return ResponseEntity.ok(categorieService.getAllByIsActiveCategory(isActive));
    }

    @GetMapping("/active/one")
    public ResponseEntity<CategoryDto> getByIsActive(@RequestParam boolean isActive) {
        return ResponseEntity.ok(categorieService.getByIsActiveCategory(isActive));
    }

    @GetMapping("/all-full")
    public ResponseEntity<List<CategoryFullDto>> getAllCategoriesFull() {
        return ResponseEntity.ok(categorieService.getAllCategoriesFull());
    }

}
