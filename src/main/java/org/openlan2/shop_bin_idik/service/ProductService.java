package org.openlan2.shop_bin_idik.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.openlan2.shop_bin_idik.dto.ProductDto;
import org.openlan2.shop_bin_idik.dto.ProductFullDto;
import org.openlan2.shop_bin_idik.entities.Product;


public interface ProductService {
    Page<ProductFullDto> getAllProductsFull(Pageable pageable);
    Page<ProductDto> getAllByIsActiveProduct(boolean isActive, Pageable pageable);
    ProductDto getByIsActiveProduct(boolean isActive);
    Product getProductById(Long id);
    Product createProduct(ProductDto dto);
    ProductDto updateProduct(Long id, ProductDto dto);
    void deleteProduct(Long id);
    Product setIsActiveProductFalse(Long id);
    Boolean getIsActiveProduct(Long id);
    Page<ProductDto> searchProducts(String searchTerm, Pageable pageable);
    Page<ProductDto> searchByNom(String nom, Pageable pageable);
    Page<ProductDto> searchByCategorie(String categorieName, Pageable pageable);
}
