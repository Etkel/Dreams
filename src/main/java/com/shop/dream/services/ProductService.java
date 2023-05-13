package com.shop.dream.services;

import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.dto.ProductDTO;
import com.shop.dream.models.Category;
import com.shop.dream.models.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll(Pageable pageable);
    List<ProductDTO> findAll();
    void deleteProductById(Long id);
    Long count();
    Product save(ProductDTO dto, String imgName);
    boolean existsByName(String name);
    ProductDTO findById(Long id);
    Product getReferenceByIdEssence(Long id);
    boolean update(ProductDTO dto, String img, Category category);
    List<CategoryDTO> findCategoriesFromProduct(Long id);
    List<ProductDTO> searchProducts(String partName,Pageable pageable);
    List<ProductDTO> getRandomProducts(Long productId);
    Long countSearchProduct(String name);

}
