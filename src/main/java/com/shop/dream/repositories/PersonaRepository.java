package com.shop.dream.repositories;

import com.shop.dream.models.Cart;
import com.shop.dream.models.Persona;
import com.shop.dream.models.enums.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByEmail(String email);
    Persona findByEmail(String email);
    @Query("SELECT COUNT (p) FROM Persona p WHERE p.role=:role")
    Long findAdminCount(@Param(value = "role") Role role);
    @Query("SELECT p.cart FROM Persona p WHERE p.email=:email")
    Cart findCartByEmail(@Param(value = "email") String email);
    @Query("SELECT COALESCE(size(c.cart.cartItems), 0) FROM Persona c WHERE c.email=:email")
    Integer findCartSizeByEmail(@Param(value = "email") String email);
    @Query("SELECT p.email FROM Persona p")
    List<String> findAllEmails();
    @Query("SELECT p FROM Persona p WHERE p.name LIKE CONCAT('%', :name, '%')")
    List<Persona> searchEmailSomething(String name, Pageable pageable);
    @Query("SELECT COUNT (p) FROM Persona p WHERE p.name LIKE CONCAT('%', :name, '%')")
    Long countSearchEmailSomething(String name);

}
