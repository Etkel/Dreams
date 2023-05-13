package com.shop.dream.services;

import com.shop.dream.dto.CartDTO;
import com.shop.dream.dto.ProductMvpDTO;

import java.security.Principal;
import java.util.List;

public interface CartItemsService {
    List<ProductMvpDTO> findProductsWithMaxAmount();
    void addItemToCart(Long productId, Integer amount, Principal principal);
    void updateItems(CartDTO dto);
    void delete(Long itemId);
}
