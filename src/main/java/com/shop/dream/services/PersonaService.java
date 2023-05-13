package com.shop.dream.services;

import com.shop.dream.dto.PersonaDTO;
import com.shop.dream.models.Cart;
import com.shop.dream.models.Persona;
import com.shop.dream.models.enums.Role;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;


public interface PersonaService extends UserDetailsService {
    boolean save(PersonaDTO personaDTO);
    List<PersonaDTO> findAll(PageRequest pageRequest);
    boolean existsByEmail(String email);
    Long count();
    PersonaDTO findByEmail(String email);
    PersonaDTO findById(Long id);
    void banUnban(PersonaDTO dto);
    Long findAdminsCount(Role role);
    void deleteById(Long id);
    boolean mailCheck(String email);
    boolean mailCheckToUpdate(String email, Principal principal);
    boolean saveByAdmin(PersonaDTO dto, Role role);
    void updatePersona(PersonaDTO dto, String rePassword);
    Cart findCartByEmailEssence(String email);
    Integer findCartSizeByEmail(String email);
    Persona findByEmailEssence(String email);
    boolean passCheck(String password);
    List<PersonaDTO> searchByEmailSomething(String email, Pageable pageable);
    Long countByEmailSearch(String email);
    String allEmailsExceptCurrent(Principal principal);
}
