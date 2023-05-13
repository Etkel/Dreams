package com.shop.dream.dto;

import com.shop.dream.models.CartItem;
import com.shop.dream.models.Persona;
import com.shop.dream.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Long id;
    private String address;
    private BigDecimal totalSum;
    private String comments;
    private LocalDateTime created;
    private LocalDateTime updated;
    private OrderStatus status;
    private Persona persona;
    private List<CartItem> cartItems;
}
