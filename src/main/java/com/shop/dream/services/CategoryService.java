package com.shop.dream.services;

import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.dto.ProductDTO;
import com.shop.dream.models.Category;
import com.shop.dream.models.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll(PageRequest pageRequest);
    List<CategoryDTO> findAll();
    boolean save(CategoryDTO dto, String imgName);
    void addProduct(Long categoryId, Product product);
    void delProduct(Long categoryId, ProductDTO dto);
    boolean existsByName(String name);
    void deleteCategoryById(Long id);
    CategoryDTO findById(Long id);
    Category findByIdEssence(Long id);
    boolean update(CategoryDTO dto, String imgName);
    List<ProductDTO> findProductsByCategoryName(String name, Pageable pageable);
    List<ProductDTO> findProductsByCategoryName(String name);
}
