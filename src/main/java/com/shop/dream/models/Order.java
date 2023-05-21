package com.shop.dream.models;

import com.shop.dream.dto.OrderDTO;
import com.shop.dream.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String address;
    private BigDecimal totalSum;
    private Long telephone;
    @Column(length = 1000)
    private String comments;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private List<CartItem> cartItems;

    public static OrderDTO orderToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.id)
                .address(order.address)
                .status(order.status)
                .persona(order.persona)
                .totalSum(order.totalSum)
                .comments(order.comments)
                .created(order.created)
                .updated(order.updated)
                .cartItems(order.cartItems)
                .build();
    }
}