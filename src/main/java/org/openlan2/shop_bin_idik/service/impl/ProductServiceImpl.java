package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.ProductDto;
import org.openlan2.shop_bin_idik.dto.ProductFullDto;
import org.openlan2.shop_bin_idik.entities.Categorie;
import org.openlan2.shop_bin_idik.entities.Product;
import org.openlan2.shop_bin_idik.mappers.CategoryMapper;
import org.openlan2.shop_bin_idik.mappers.ProductMapper;
import org.openlan2.shop_bin_idik.repository.CategorieRepository;
import org.openlan2.shop_bin_idik.repository.ProductRepository;
import org.openlan2.shop_bin_idik.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategorieRepository categorieRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Product createProduct(ProductDto dto) {
        Product product = productMapper.toEntity(dto);
        product.setIsActiveProduct(true);
        product.setDateCreated(LocalDateTime.now());
        
        if (dto.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategorie(categorie);
        }
        
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        product.setNom(dto.getNom());
        product.setDescription(dto.getDescription());
        product.setPrix(dto.getPrix());
        product.setSizes(dto.getSizes());
        product.setStock(dto.getStock());
        product.setStatus(dto.getStatus());
        
        if (dto.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategorie(categorie);
        }
        
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllByIsActiveProduct(boolean isActive) {
        return productRepository.findAllByIsActiveProduct(isActive)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getByIsActiveProduct(boolean isActive) {
        return productRepository.findFirstByIsActiveProduct(isActive)
                .map(productMapper::toDto)
                .orElse(null);
    }

    @Override
    public Product setIsActiveProductFalse(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setIsActiveProduct(!product.getIsActiveProduct());
        return productRepository.save(product);
    }

    @Override
    public List<ProductFullDto> getAllProductsFull() {
        return productRepository.findAll()
                .stream()
                .map(product -> {
                    ProductFullDto dto = productMapper.toFullDto(product);
                    if (product.getCategorie() != null) {
                        dto.setCategorie(categoryMapper.toDto(product.getCategorie()));
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<ProductDto> searchProducts(String searchTerm) {
        List<Product> products = productRepository.searchByNomOrCategorie(searchTerm);
        return products.stream()
            .map(productMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByNom(String nom) {
        List<Product> products = productRepository.findByNomContainingIgnoreCase(nom);
        return products.stream()
            .map(productMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByCategorie(String categorieName) {
        List<Product> products = productRepository.findByCategorieNomContainingIgnoreCase(categorieName);
        return products.stream()
            .map(productMapper::toDto)
            .collect(Collectors.toList());
    }
}
