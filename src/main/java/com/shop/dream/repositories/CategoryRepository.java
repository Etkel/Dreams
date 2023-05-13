package com.shop.dream.repositories;

import com.shop.dream.models.Category;
import com.shop.dream.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameCategory(String name);
    Category findByNameCategory(String name);
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.nameCategory=:name")
    Page<Product> findProductsByCategoryName(@Param("name") String name, Pageable pageable);
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.nameCategory=:name")
    List<Product> findProductsByCategoryName(@Param("name") String name);
}
