package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.CategoryDto;
import org.openlan2.shop_bin_idik.dto.CategoryFullDto;
import org.openlan2.shop_bin_idik.entities.Categorie;
import java.util.List;

public interface CategorieService {
    List<CategoryFullDto> getAllCategoriesFull();
    List<CategoryDto> getAllByIsActiveCategory(boolean isActive);
    CategoryDto getByIsActiveCategory(boolean isActive);
    Categorie getCategoryById(Long id);
    Categorie createCategory(CategoryDto dto);
    Categorie updateCategory(Long id, CategoryDto dto);
    void deleteCategory(Long id);
    Categorie setIsActiveCategoryFalse(Long id);
}