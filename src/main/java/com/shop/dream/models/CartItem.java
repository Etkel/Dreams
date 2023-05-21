package com.shop.dream.models;


import com.shop.dream.dto.CartItemDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "CartItems")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public static CartItemDTO cartItemToDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                .id(cartItem.id)
                .product(cartItem.product)
                .amount(cartItem.amount)
                .cart(cartItem.cart)
                .build();
    }
}
