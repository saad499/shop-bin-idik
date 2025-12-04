package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.CategoryDto;
import org.openlan2.shop_bin_idik.dto.CategoryFullDto;
import org.openlan2.shop_bin_idik.entities.Categorie;
import org.openlan2.shop_bin_idik.mappers.CategoryMapper;
import org.openlan2.shop_bin_idik.repository.CategorieRepository;
import org.openlan2.shop_bin_idik.service.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Categorie createCategory(CategoryDto dto) {
        Categorie categorie = categoryMapper.toEntity(dto);
        categorie.setIsActiveCategory(true);
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategory(Long id, CategoryDto dto) {
        Categorie categorie = categorieRepository.findById(id).orElseThrow();
        categorie.setNom(dto.getNom());
        categorie.setDescription(dto.getDescription());
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategory(Long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllByIsActiveCategory(boolean isActive) {
        return categorieRepository.findAllByIsActiveCategory(isActive)
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getByIsActiveCategory(boolean isActive) {
        return categorieRepository.findFirstByIsActiveCategory(isActive)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Override
    public Categorie setIsActiveCategoryFalse(Long id) {
        Categorie categorie = categorieRepository.findById(id).orElseThrow();
        categorie.setIsActiveCategory(false);
        return categorieRepository.save(categorie);
    }

    @Override
    public List<CategoryFullDto> getAllCategoriesFull() {
        return categorieRepository.findAll()
                .stream()
                .map(categoryMapper::toFullDto)
                .collect(java.util.stream.Collectors.toList());
    }
}
