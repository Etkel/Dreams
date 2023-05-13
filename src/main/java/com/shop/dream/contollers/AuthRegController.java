package com.shop.dream.contollers;

import com.shop.dream.dto.PersonaDTO;
import com.shop.dream.services.PersonaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class AuthRegController {
    private final PersonaServiceImpl personaService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("persona", new PersonaDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String savePersona(Model model, PersonaDTO dto) {
        if(personaService.existsByEmail(dto.getEmail())) {
            return "redirect:/registration?exist";
        }
        if (personaService.save(dto)) {
            return "redirect:/login";
        } else {
            model.addAttribute("persona", dto);
        }
        return "registration";
    }



}
