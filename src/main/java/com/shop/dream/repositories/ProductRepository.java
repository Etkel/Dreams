package com.shop.dream.repositories;

import com.shop.dream.models.Category;
import com.shop.dream.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    @Query("SELECT p.categories FROM Product p WHERE p.id=:productId")
    List<Category> findCategoriesFromProduct(@Param("productId") Long productId);
    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')")
    List<Product> searchProductByName(String name, Pageable pageable);
    @Query("SELECT COUNT (p) FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')")
    Long countSearchProductByName(String name);
}