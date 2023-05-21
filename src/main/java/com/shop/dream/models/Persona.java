package com.shop.dream.models;


import com.shop.dream.dto.PersonaDTO;
import com.shop.dream.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private Role role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    @ToString.Exclude
    private Cart cart;
    @OneToMany(mappedBy = "persona")
    @ToString.Exclude
    private List<Order> orders;

    public static PersonaDTO personaToDTO(Persona persona) {
        return PersonaDTO.builder()
                .id(persona.id)
                .name(persona.name)
                .lastName(persona.lastName)
                .email(persona.email)
                .role(persona.role)
                .cart(persona.cart)
                .build();
    }

    @PostPersist
    private void cartCreation() {
        Cart cart = new Cart();
        this.setCart(cart);
        cart.setPersona(this);
    }
}
