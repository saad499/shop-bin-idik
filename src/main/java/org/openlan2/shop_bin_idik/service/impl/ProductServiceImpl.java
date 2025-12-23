package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.*;
import org.openlan2.shop_bin_idik.entities.Categorie;
import org.openlan2.shop_bin_idik.entities.Color;
import org.openlan2.shop_bin_idik.entities.Size;
import org.openlan2.shop_bin_idik.entities.Image;
import org.openlan2.shop_bin_idik.entities.Product;
import org.openlan2.shop_bin_idik.mappers.CategoryMapper;
import org.openlan2.shop_bin_idik.mappers.ProductMapper;
import org.openlan2.shop_bin_idik.repository.*;
import org.openlan2.shop_bin_idik.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategorieRepository categorieRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final ImageRepository imageRepository;
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
        List<Size> existingSizes = sizeRepository.findByProductId(savedProduct.getId());

        List<Size> sizes = dto.getSizes().stream()
                .filter(sizeDto -> existingSizes.stream()
                        .noneMatch(existingSize -> existingSize.getSizeName().equalsIgnoreCase(sizeDto.getSizeName())))
                .map(sizeDto -> Size.builder()
                        .sizeName(sizeDto.getSizeName())
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
        if (!sizes.isEmpty()) {
            savedProduct.setSizes(sizes);
        }
    }
    
    // Handle Colors
        if (dto.getColors() != null && !dto.getColors().isEmpty()) {
            List<Color> existingColors = colorRepository.findByProductId(savedProduct.getId());

            List<Color> colors = dto.getColors().stream()
                    .filter(colorDto -> existingColors.stream()
                            .noneMatch(existingColor ->
                                    existingColor.getColorName().equalsIgnoreCase(colorDto.getColorName()) &&
                                            (colorDto.getColorCode() == null ||
                                                    existingColor.getColorCode().equalsIgnoreCase(colorDto.getColorCode()))))
                    .map(colorDto -> Color.builder()
                            .colorName(colorDto.getColorName())
                            .colorCode(colorDto.getColorCode())
                            .product(savedProduct)
                            .build())
                    .collect(Collectors.toList());

            if (!colors.isEmpty()) {
                savedProduct.setColors(colors);
            }
        }
    
    // Handle Images
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            List<Image> existingImages = imageRepository.findByProductId(savedProduct.getId());

            List<Image> images = dto.getImages().stream()
                    .filter(imageDto -> existingImages.stream()
                            .noneMatch(existingImage -> existingImage.getImageUrl().equalsIgnoreCase(imageDto.getImageUrl())))
                    .map(imageDto -> Image.builder()
                            .imageUrl(imageDto.getImageUrl())
                            .product(savedProduct)
                            .build())
                    .collect(Collectors.toList());

            if (!images.isEmpty()) {
                savedProduct.setImages(images);
            }
        }
    
    return productRepository.save(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto dto) {
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
            List<Size> existingSizes = product.getSizes() != null ? product.getSizes() : new ArrayList<>();

            List<Size> newSizes = dto.getSizes().stream()
                    .filter(sizeDto -> existingSizes.stream()
                            .noneMatch(existingSize -> existingSize.getSizeName().equalsIgnoreCase(sizeDto.getSizeName())))
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
            List<Color> existingColors = product.getColors() != null ? product.getColors() : new ArrayList<>();

            List<Color> newColors = dto.getColors().stream()
                    .filter(colorDto -> existingColors.stream()
                            .noneMatch(existingColor ->
                                    existingColor.getColorName().equalsIgnoreCase(colorDto.getColorName()) &&
                                            (colorDto.getColorCode() == null ||
                                                    existingColor.getColorCode().equalsIgnoreCase(colorDto.getColorCode()))))
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
            List<Image> existingImages = product.getImages() != null ? product.getImages() : new ArrayList<>();

            List<Image> newImages = dto.getImages().stream()
                    .filter(imageDto -> existingImages.stream()
                            .noneMatch(existingImage -> existingImage.getImageUrl().equalsIgnoreCase(imageDto.getImageUrl())))
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
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDto> getAllByIsActiveProduct(boolean isActive, Pageable pageable) {
        Page<Product> products = productRepository.findAllByIsActiveProduct(isActive, pageable);
    return products.map(productMapper::toDto);
    }

    @Override
    public ProductDto getByIsActiveProduct(boolean isActive) {
        return productRepository.findFirstByIsActiveProduct(isActive)
                .map(productMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProductDto setIsActiveProductFalse(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setIsActiveProduct(!product.getIsActiveProduct());
        Product activeProduct = productRepository.save(product);
        return productMapper.toDto(activeProduct);
    }

    @Override
    public Page<ProductFullDto> getAllProductsFull(Pageable pageable) {
    Page<Product> products = productRepository.findAll(pageable);
    return products.map(product -> {
        ProductFullDto dto = productMapper.toFullDto(product);
        if (product.getCategorie() != null) {
            dto.setCategorie(categoryMapper.toDto(product.getCategorie()));
        }
        return dto;
    });
}

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Page<ProductDto> searchProducts(String searchTerm, Pageable pageable) {
        Page<Product> products = productRepository.searchByNomOrCategorie(searchTerm, pageable);
        return products.map(productMapper::toDto);
    }

    @Override
    public Page<ProductDto> searchByNom(String nom, Pageable pageable) {
        Page<Product> products = productRepository.findByNomContainingIgnoreCase(nom, pageable);
        return products.map(productMapper::toDto);
    }

    @Override
    public Page<ProductDto> searchByCategorie(String categorieName, Pageable pageable) {
        Page<Product> products = productRepository.findByCategorieNomContainingIgnoreCase(categorieName, pageable);
        return products.map(productMapper::toDto);
    }

    @Override
    public Boolean getIsActiveProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return product.getIsActiveProduct();
    }

    @Override
    public Page<ProductActiveDto> getAllActiveProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAllByStatusActif(pageable);
        return products.map(product -> ProductActiveDto.builder()
                .nom(product.getNom())
                .image(product.getImages() != null && !product.getImages().isEmpty() ? 
                    ImageDto.builder().imageUrl(product.getImages().get(0).getImageUrl()).build() : 
                    null)
                .build());
    }
}
