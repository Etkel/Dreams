package com.shop.dream.contollers;

import com.shop.dream.components.EmailSender;
import com.shop.dream.services.OrderService;
import com.shop.dream.services.PersonaService;
import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.dto.PersonaDTO;
import com.shop.dream.models.Order;
import com.shop.dream.models.enums.Role;
import com.shop.dream.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaService personaService;
    private final CategoryService categoryService;
    private final OrderService orderService;
    private static final Integer COUNT_PAGE_PERSONAS = 10;
    private static final Integer COUNT_PAGE_ORDER = 12;
    private final EmailSender emailSender;

    @GetMapping("/admin/personas")
    public String personaList(Model model, Principal principal,
                              @RequestParam(required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                              @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
                              @RequestParam(value = "emailSearch", required = false) String emailSearch) {
        if (emailSearch != null) {
            List<PersonaDTO> personaDTOS = personaService.searchByEmailSomething(emailSearch,
                    PageRequest.of(page, COUNT_PAGE_PERSONAS, Sort.Direction.valueOf(sortDir), sortField));
            model.addAttribute("currentPage", page);
            model.addAttribute("personaList", personaDTOS);
            model.addAttribute("allPages", getPageCountSearch(emailSearch));
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("emailSearch", emailSearch);
            model.addAttribute("reverseSort", sortDir.equals("ASC") ? "DESC" : "ASC");

        } else {
            List<PersonaDTO> personaDTOs = personaService.findAll(PageRequest.of(page, COUNT_PAGE_PERSONAS,
                    Sort.Direction.valueOf(sortDir), sortField));
            model.addAttribute("currentPage", page);
            model.addAttribute("personaList", personaDTOs);
            model.addAttribute("allPages", getPageCountPersona());
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSort", sortDir.equals("ASC") ? "DESC" : "ASC");
        }
//Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "personas";
    }

    @PostMapping("/admin/general-email")
    public String generalEmailSend(String body, String topic, Principal principal) {
        emailSender.sendEmail(personaService.allEmailsExceptCurrent(principal), topic, body);
        return "redirect:/admin/personas?mails";

    }


    @GetMapping("/admin/new-persona")
    public String personaForm(Model model) {
        model.addAttribute("persona", new PersonaDTO());
        model.addAttribute("roles", Role.values());
        return "persona-add";
    }

    @PostMapping("/admin/new-persona")
    public String addPersona(@ModelAttribute(name = "persona") PersonaDTO personaDTO) {
        if (!personaService.mailCheck(personaDTO.getEmail())) {
            return "redirect:/admin/new-persona?invalidmail";
        }
        if (personaService.existsByEmail(personaDTO.getEmail())) {
            return "redirect:/admin/new-persona?exist";
        }
        if (!personaService.passCheck(personaDTO.getPassword())) {
            return "redirect:/admin/new-persona?short";
        }
        personaService.saveByAdmin(personaDTO, personaDTO.getRole());
        return "redirect:/admin/personas";
    }

    @GetMapping("/admin/personas/ban/{id}")
    public String banUnban(@PathVariable Long id) {
        PersonaDTO persona = personaService.findById(id);
        if (persona.getRole() == Role.ADMIN) {
            return "redirect:/admin/personas?nope";
        }

        personaService.banUnban(persona);
        return "redirect:/admin/personas";
    }

    @GetMapping("/admin/personas/delete/{id}")
    public String delete(@PathVariable Long id) {
        Role role = personaService.findById(id).getRole();
        if (role == Role.ADMIN) {
            if (personaService.findAdminsCount(role) <= 1) {
                return "redirect:/admin/personas?aloneInTheField";
            }
        }
        personaService.deleteById(id);
        return "redirect:/admin/personas";
    }

    @GetMapping("/admin/personas/orders/{email}")
    public String orders(Model model,
                         @PathVariable("email") String email, Principal principal,
                         @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<Order> orders = orderService.findOrdersByEmail(email,
                PageRequest.of(page, COUNT_PAGE_ORDER, Sort.Direction.DESC, "id"));
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("allPages", getPageCountOrders(email));
        //Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "order-list";
    }

    private long getPageCountPersona() {
        long totalCount = personaService.count();
        var pageCount = (totalCount / COUNT_PAGE_PERSONAS) + ((totalCount % COUNT_PAGE_PERSONAS > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }

    private long getPageCountSearch(String email) {
        long totalCount = personaService.countByEmailSearch(email);
        var pageCount = (totalCount / COUNT_PAGE_PERSONAS) + ((totalCount % COUNT_PAGE_PERSONAS > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }

    private long getPageCountOrders(String email) {
        long totalCount = orderService.ordersCountByMail(email);
        var pageCount = (totalCount / COUNT_PAGE_ORDER) + ((totalCount % COUNT_PAGE_ORDER > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }
}
