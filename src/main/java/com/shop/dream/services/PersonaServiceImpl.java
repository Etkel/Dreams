package com.shop.dream.services;

import com.shop.dream.dto.PersonaDTO;
import com.shop.dream.models.Cart;
import com.shop.dream.models.Persona;
import com.shop.dream.models.enums.Role;
import com.shop.dream.repositories.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.shop.dream.models.Persona.personaToDTO;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {
    private static final HashSet<String> ALLOWED_DOMAINS = new HashSet<>(Arrays.asList("gmail.com", "yahoo.com",
            "outlook.com", "hotmail.com", "aol.com", "icloud.com", "mail.com", "protonmail.com", "gmx.com", "ukr.net",
            "mail.ua", "i.ua", "meta.ua", "ex.ua"));
    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public boolean save(PersonaDTO dto) {
        Persona persona = Persona.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();
        personaRepository.save(persona);
        return true;
    }

    @Override
    @Transactional
    public boolean saveByAdmin(PersonaDTO dto, Role role) {
        Persona persona = Persona.builder()
                .id(dto.getId())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(role)
                .build();
        personaRepository.save(persona);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaDTO> findAll(PageRequest pageRequest) {
        Page<Persona> page = personaRepository.findAll(pageRequest);
        return page.getContent().stream().map(Persona::personaToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByEmail(email);
        if (persona == null)
            throw new UsernameNotFoundException("Persona not found by email: " + email);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(persona.getRole().name()));
        return new User(persona.getEmail(), persona.getPassword(), roles);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        return personaRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public Long count() {
        return personaRepository.count();
    }

    @Transactional(readOnly = true)
    @Override
    public PersonaDTO findByEmail(String email) {
        return personaToDTO(personaRepository.findByEmail(email));
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaDTO findById(Long id) {
        if (personaRepository.findById(id).isPresent()) {
            return personaToDTO(personaRepository.findById(id).get());
        }
        return null;
    }

    @Override
    @Transactional
    public void banUnban(PersonaDTO dto) {
        Persona persona = personaRepository.findByEmail(dto.getEmail());
        if (persona.getRole() == Role.USER) {
            persona.setRole(Role.BANNED);
        } else {
            persona.setRole(Role.USER);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long findAdminsCount(Role role) {
        return personaRepository.findAdminCount(role);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public boolean mailCheck(String email) {
        System.out.println(ALLOWED_DOMAINS);
        String[] parts = email.split("@");
        String domain = parts[1];
        for (String allowedDomain : ALLOWED_DOMAINS) {
            if (domain.equals(allowedDomain)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public void updatePersona(PersonaDTO dto, String rePassword) {
        if (personaRepository.findById(dto.getId()).isPresent()) {
            Persona persona = personaRepository.findById(dto.getId()).get();
            if (!dto.getName().isBlank()) {
                persona.setName(dto.getName());
            }
            if (!dto.getLastName().isBlank()) {
                persona.setLastName(dto.getLastName());
            }
            if (!dto.getEmail().isBlank()) {
                persona.setEmail(dto.getEmail());
            }
            if (!dto.getPassword().isEmpty() && (dto.getPassword()).equals(rePassword)) {
                persona.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean mailCheckToUpdate(String email, Principal principal) {
        List<String> emails = personaRepository.findAllEmails();
        if (!principal.getName().equals(email)) {
            emails.remove(principal.getName());
            return !emails.contains(email);
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart findCartByEmailEssence(String email) {
        Cart cart = personaRepository.findCartByEmail(email);
        if (cart.getCartItems() == null) {
            cart.setCartItems(new ArrayList<>());
        }
        return cart;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer findCartSizeByEmail(String email) {
        return personaRepository.findCartSizeByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findByEmailEssence(String email) {
        return personaRepository.findByEmail(email);
    }

    @Override
    public boolean passCheck(String password) {
        return password.length() >= 8;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonaDTO> searchByEmailSomething(String email, Pageable pageable) {
        return personaRepository.searchEmailSomething(email, pageable).stream()
                .map(Persona::personaToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByEmailSearch(String email) {
        return personaRepository.countSearchEmailSomething(email);
    }

    @Override
    @Transactional(readOnly = true)
    public String allEmailsExceptCurrent(Principal principal) {
        List<String> emails = personaRepository.findAllEmails();
        emails.remove(principal.getName());

        StringBuilder sb = new StringBuilder();
        for (String temp : emails) {
            sb.append(temp).append(", ");
        }
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(" "));
        return sb.toString();
    }
}
