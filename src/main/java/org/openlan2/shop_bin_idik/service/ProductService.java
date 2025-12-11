package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.ProductDto;
import org.openlan2.shop_bin_idik.dto.ProductFullDto;
import org.openlan2.shop_bin_idik.entities.Product;

import java.util.List;

public interface ProductService {
    List<ProductFullDto> getAllProductsFull();
    List<ProductDto> getAllByIsActiveProduct(boolean isActive);
    ProductDto getByIsActiveProduct(boolean isActive);
    Product getProductById(Long id);
    Product createProduct(ProductDto dto);
    Product updateProduct(Long id, ProductDto dto);
    void deleteProduct(Long id);
    Product setIsActiveProductFalse(Long id);
    Boolean getIsActiveProduct(Long id);
    List<ProductDto> searchProducts(String searchTerm);
    List<ProductDto> searchByNom(String nom);
    List<ProductDto> searchByCategorie(String categorieName);
}
