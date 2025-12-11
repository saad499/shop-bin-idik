package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.ProductDto;
import org.openlan2.shop_bin_idik.dto.ProductFullDto;
import org.openlan2.shop_bin_idik.entities.Product;
import org.openlan2.shop_bin_idik.service.ProductService;
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
    public ResponseEntity<Product> update(@RequestParam Long id, @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @PostMapping("/activate-desactivate")
    public ResponseEntity<Product> deactivateProduct(@RequestParam Long id) {
        Product updated = productService.setIsActiveProductFalse(id);
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
    public ResponseEntity<List<ProductDto>> getAllByIsActive(@RequestParam boolean isActive) {
        return ResponseEntity.ok(productService.getAllByIsActiveProduct(isActive));
    }

    @GetMapping("/active/one")
    public ResponseEntity<ProductDto> getByIsActive(@RequestParam boolean isActive) {
        return ResponseEntity.ok(productService.getByIsActiveProduct(isActive));
    }

    @GetMapping("/all-full")
    public ResponseEntity<List<ProductFullDto>> getAllProductsFull() {
        return ResponseEntity.ok(productService.getAllProductsFull());
    }

    @GetMapping("")
    public ResponseEntity<Product> getById(@RequestParam Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam String searchTerm) {
        return ResponseEntity.ok(productService.searchProducts(searchTerm));
    }

    @GetMapping("/search/nom")
    public ResponseEntity<List<ProductDto>> searchByNom(@RequestParam String nom) {
        return ResponseEntity.ok(productService.searchByNom(nom));
    }

    @GetMapping("/search/categorie")
    public ResponseEntity<List<ProductDto>> searchByCategorie(@RequestParam String categorieName) {
        return ResponseEntity.ok(productService.searchByCategorie(categorieName));
    }
}
