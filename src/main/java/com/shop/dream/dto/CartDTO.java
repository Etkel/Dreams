package com.shop.dream.dto;

import com.shop.dream.models.CartItem;
import com.shop.dream.models.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private Persona persona;
    private List<CartItem> cartItems;

}
