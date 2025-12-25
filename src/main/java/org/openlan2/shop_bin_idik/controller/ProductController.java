package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.ProductActiveDto;
import org.openlan2.shop_bin_idik.dto.ProductDto;
import org.openlan2.shop_bin_idik.dto.ProductFullDto;
import org.openlan2.shop_bin_idik.entities.Product;
import org.openlan2.shop_bin_idik.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<ProductDto> update(@RequestParam Long id, @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @PostMapping("/activate-desactivate")
    public ResponseEntity<ProductDto> deactivateProduct(@RequestParam Long id) {
        ProductDto updated = productService.setIsActiveProductFalse(id);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/is-active")
    public ResponseEntity<Boolean> getIsActive(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getIsActiveProduct(id));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<Page<ProductDto>> getAllByIsActive(
            @RequestParam boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllByIsActiveProduct(isActive, pageable));
    }

    @GetMapping("/active/one")
    public ResponseEntity<ProductDto> getByIsActive(@RequestParam boolean isActive) {
        return ResponseEntity.ok(productService.getByIsActiveProduct(isActive));
    }

    @GetMapping("/all-full")
    public ResponseEntity<Page<ProductFullDto>> getAllProductsFull(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllProductsFull(pageable));
    }

    @GetMapping("")
    public ResponseEntity<Product> getById(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    
    @GetMapping("/search")
    public ResponseEntity<Page<ProductDto>> searchProducts(
            @RequestParam String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.searchProducts(searchTerm, pageable));
    }

    @GetMapping("/search/nom")
    public ResponseEntity<Page<ProductDto>> searchByNom(
            @RequestParam String nom,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.searchByNom(nom, pageable));
    }

    @GetMapping("/search/categorie")
    public ResponseEntity<Page<ProductDto>> searchByCategorie(
            @RequestParam String categorieName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.searchByCategorie(categorieName, pageable));
    }

    @GetMapping("/status/active")
    public ResponseEntity<Page<ProductActiveDto>> getAllActiveProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllActiveProducts(pageable));
    }

    @GetMapping("/search/actif")
    public ResponseEntity<Page<ProductActiveDto>> searchProductActif(
            @RequestParam String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.searchProductActif(searchTerm, pageable));
    }
}
