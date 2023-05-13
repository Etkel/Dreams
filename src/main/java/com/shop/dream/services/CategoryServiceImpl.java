package com.shop.dream.services;

import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.dto.ProductDTO;
import com.shop.dream.models.Category;
import com.shop.dream.models.Product;
import com.shop.dream.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Value("${gcp.bucket}")
    private String http;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(PageRequest pageRequest) {
        Page<Category> page = categoryRepository.findAll(pageRequest);
        return page.getContent().stream().map(Category::categoryToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(Category::categoryToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean save(CategoryDTO dto, String imgName) {
        Category category = Category.builder()
                .nameCategory(dto.getNameCategory())
                .description(dto.getDescription())
                .imageNameCategory(http + imgName)
                .build();

        categoryRepository.save(category);
        return true;
    }

    @Override
    @Transactional
    public void addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId).get();
        if (product.getCategories() == null) {
            product.setCategories(new ArrayList<>());
        }
            product.getCategories().add(category);
    }

    @Override
    @Transactional
    public void delProduct(Long categoryId, ProductDTO dto) {
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).get();
            if (dto.getCategories() == null) {
                dto.setCategories(new ArrayList<>());
            }
            dto.getCategories().remove(category);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return categoryRepository.existsByNameCategory(name);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresent(category -> category.getProductList()
                .forEach(product -> product.getCategories().remove(category)));
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        var category = categoryRepository.findById(id);
        return category.map(Category::categoryToDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByIdEssence(Long id) {
        if (id != null) {
            if (categoryRepository.findById(id).isPresent()) {
                return categoryRepository.findById(id).get();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public boolean update(CategoryDTO dto, String imgName) {
        if (categoryRepository.findById(dto.getId()).isPresent()) {
            Category categoryFromDB = categoryRepository.findById(dto.getId()).get();
            categoryFromDB.setNameCategory(dto.getNameCategory());
            if (imgName != null && !imgName.isBlank()) {
                categoryFromDB.setImageNameCategory(http + imgName);
            }
            if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
                categoryFromDB.setDescription(dto.getDescription());
            }
            categoryRepository.save(categoryFromDB);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsByCategoryName(String name, Pageable pageable) {
        try {
            Page<Product> products = categoryRepository.findProductsByCategoryName(name,pageable);
            return products.getContent().stream().map(Product::productToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Exception in findProductsByCategoryName: " + e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findProductsByCategoryName(String name) {
        return categoryRepository.findProductsByCategoryName(name).stream()
                .map(Product::productToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Category findByNameEssence(String name) {
        return categoryRepository.findByNameCategory(name);
    }

}
