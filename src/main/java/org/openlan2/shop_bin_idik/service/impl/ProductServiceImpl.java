package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.ProductDto;
import org.openlan2.shop_bin_idik.dto.ProductFullDto;
import org.openlan2.shop_bin_idik.entities.Categorie;
import org.openlan2.shop_bin_idik.entities.Color;
import org.openlan2.shop_bin_idik.entities.Size;
import org.openlan2.shop_bin_idik.entities.Image;
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
        Product product = Product.builder()
                .nom(dto.getNom())
                .description(dto.getDescription())
                .prix(dto.getPrix())
                .stock(dto.getStock())
                .status(dto.getStatus())
                .isActiveProduct(true)
                .dateCreated(LocalDateTime.now())
                .build();

        if (dto.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategorie(categorie);
        }
    
    // Save product first to get the ID
    Product savedProduct = productRepository.save(product);
    
    // Handle Sizes
    if (dto.getSizes() != null && !dto.getSizes().isEmpty()) {
        List<Size> sizes = dto.getSizes().stream()
                .map(sizeDto -> Size.builder()
                        .sizeName(sizeDto.getSizeName())
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
        savedProduct.setSizes(sizes);
    }
    
    // Handle Colors
    if (dto.getColors() != null && !dto.getColors().isEmpty()) {
        List<Color> colors = dto.getColors().stream()
                .map(colorDto -> Color.builder()
                        .colorName(colorDto.getColorName())
                        .colorCode(colorDto.getColorCode())
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
        savedProduct.setColors(colors);
    }
    
    // Handle Images
    if (dto.getImages() != null && !dto.getImages().isEmpty()) {
        List<Image> images = dto.getImages().stream()
                .map(imageDto -> Image.builder()
                        .imageUrl(imageDto.getImageUrl())
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
        savedProduct.setImages(images);
    }
    
    return productRepository.save(savedProduct);
    }

    @Override
    public Product updateProduct(Long id, ProductDto dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        product.setNom(dto.getNom());
        product.setDescription(dto.getDescription());
        product.setPrix(dto.getPrix());
        product.setStock(dto.getStock());
        product.setStatus(dto.getStatus());
        
        if (dto.getCategorieId() != null) {
            Categorie categorie = categorieRepository.findById(dto.getCategorieId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategorie(categorie);
        }
        // Update Sizes
        if (dto.getSizes() != null && !dto.getSizes().isEmpty()) {
            List<Size> newSizes = dto.getSizes().stream()
                    .map(sizeDto -> Size.builder()
                            .sizeName(sizeDto.getSizeName())
                            .product(product)
                            .build())
                    .collect(Collectors.toList());
            
            if (product.getSizes() == null) {
                product.setSizes(newSizes);
            } else {
                product.getSizes().addAll(newSizes);
            }
        }

        // Update Colors
        if (dto.getColors() != null && !dto.getColors().isEmpty()) {
            List<Color> newColors = dto.getColors().stream()
                    .map(colorDto -> Color.builder()
                            .colorName(colorDto.getColorName())
                            .colorCode(colorDto.getColorCode())
                            .product(product)
                            .build())
                    .collect(Collectors.toList());
            
            if (product.getColors() == null) {
                product.setColors(newColors);
            } else {
                product.getColors().addAll(newColors);
            }
        }
        
        // Update Images
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            List<Image> newImages = dto.getImages().stream()
                    .map(imageDto -> Image.builder()
                            .imageUrl(imageDto.getImageUrl())
                            .product(product)
                            .build())
                    .collect(Collectors.toList());
            
            if (product.getImages() == null) {
                product.setImages(newImages);
            } else {
                product.getImages().addAll(newImages);
            }
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

    @Override
    public Boolean getIsActiveProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return product.getIsActiveProduct();
    }
}
