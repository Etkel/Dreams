package com.shop.dream.dto;

import com.shop.dream.models.Cart;
import com.shop.dream.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Cart cart;

}
