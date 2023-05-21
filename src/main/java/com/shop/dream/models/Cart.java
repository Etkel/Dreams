package com.shop.dream.models;

import com.shop.dream.dto.CartDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    @ToString.Exclude
    private List<CartItem> cartItems;

    public static CartDTO cartToDTO(Cart cart) {
        return CartDTO.builder()
                .id(cart.id)
                .persona(cart.persona)
                .build();
    }
}
