package com.shop.dream.dto;

import com.shop.dream.models.Cart;
import com.shop.dream.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDTO {
    private Long id;
    private Cart cart;
    private Product product;
    private Integer amount;
}
