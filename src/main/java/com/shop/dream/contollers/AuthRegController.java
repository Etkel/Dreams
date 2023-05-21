package com.shop.dream.contollers;

import com.shop.dream.dto.PersonaDTO;
import com.shop.dream.services.PersonaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


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
    public String savePersona(PersonaDTO dto, String rePassword) {
        if (!personaService.mailCheck(dto.getEmail())) {
            return "redirect:/registration?invalidmail";
        }
        if(personaService.existsByEmail(dto.getEmail())) {
            return "redirect:/registration?exist";
        }
        if (!personaService.passCheck(dto.getPassword())) {
            return "redirect:/registration?short";
        }
        if (!dto.getPassword().equals(rePassword)) {
            return "redirect:/registration?wrong";
        }
        if (personaService.save(dto)) {
            return "redirect:/login";
        }
        return "registration";
    }

}
