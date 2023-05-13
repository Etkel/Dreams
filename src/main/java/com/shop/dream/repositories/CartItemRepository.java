package com.shop.dream.repositories;

import com.shop.dream.models.CartItem;
import com.shop.dream.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT c.product FROM CartItem c JOIN c.product p GROUP BY p " +
            "ORDER BY MAX(c.amount) DESC")
    List<Product> findProductsWithMaxAmount();
}
