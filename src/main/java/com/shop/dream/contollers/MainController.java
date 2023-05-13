package com.shop.dream.contollers;

import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.dto.PersonaDTO;
import com.shop.dream.dto.ProductDTO;
import com.shop.dream.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private static final Integer COUNT_PAGE_PRODUCTS = 8;
    private static final Integer COUNT_PAGE_ALL = 12;
    private final CategoryService categoryService;
    private final CartItemsService cartItemsService;
    private final ProductService productService;
    private final PersonaService personaService;
    private final OrderService orderService;


    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categories", categoryDTOS);
        model.addAttribute("principal", principal);
        System.out.println(cartItemsService.findProductsWithMaxAmount());
        model.addAttribute("mvps", cartItemsService.findProductsWithMaxAmount());
//Navigation
        List<CategoryDTO> categoryNav = categoryService.findAll();
        model.addAttribute("categoryNav", categoryNav);
        if (principal != null){
            model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        }
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/404")
    public String error404() {
        return "error";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping("/category/{categoryName}")
    public String categoryProducts(Model model, @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @PathVariable String categoryName, Principal principal) {
        model.addAttribute("principal", principal);
        if (categoryName.equals("allproducts")) {
            List<ProductDTO> productsDTOS = productService.findAll(PageRequest.of(page, COUNT_PAGE_ALL));
            model.addAttribute("products", productsDTOS);
            model.addAttribute("allPages", getPageCountAllProducts());
        } else if (categoryService.existsByName(categoryName)) {
            List<ProductDTO> productsDTOS =
                    categoryService.findProductsByCategoryName(categoryName,
                            PageRequest.of(page, COUNT_PAGE_PRODUCTS, Sort.Direction.ASC, "id"));
            model.addAttribute("products", productsDTOS);
            model.addAttribute("allPages", getPageCountCategory(categoryName));
        } else {
            List<ProductDTO> productsDTOS = productService.searchProducts(categoryName,
                    PageRequest.of(page, COUNT_PAGE_ALL));
            model.addAttribute("products", productsDTOS);
            model.addAttribute("allPages", getPageCountBySearch(categoryName));
            model.addAttribute("categoryName", categoryName);
        }
//Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        if (principal != null){
            model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        }
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
//pagination
        model.addAttribute("currentCategory", categoryName);
        model.addAttribute("currentPage", page);
        return "index-cat";
    }

    @PostMapping("/search")
    public String search(@RequestParam("categoryName") String categoryName) {
        return "redirect:/category/" + categoryName.trim();
    }

    @GetMapping("/category/")
    public String redirect() {
        return "redirect:/";
    }

    @GetMapping("/user/profile")
    public String profile(Model model, Principal principal) {
        PersonaDTO personaDTO = personaService.findByEmail(principal.getName());
        model.addAttribute("persona", personaDTO);
        model.addAttribute("rePassword", "");
        return "profile";
    }

    @PostMapping("/user/profile")
    public String profile(PersonaDTO personaDTO, String rePassword, Principal principal) {
        if (!personaService.passCheck(personaDTO.getPassword())) {
            return "redirect:/user/persona?short";
        }
        if (!personaDTO.getPassword().equals(rePassword)) {
            return "redirect:/user/profile?wrong";
        }
        if(!personaService.mailCheck(personaDTO.getEmail())) {
            return "redirect:/user/profile?invalidmail";
        }
        if(personaService.mailCheckToUpdate(personaDTO.getEmail(), principal)){
            personaService.updatePersona(personaDTO, rePassword);
        } else {
            return "redirect:/user/profile?exist";
        }
        return "redirect:/logout";
    }

    @GetMapping("/about")
    public String about(Model model, Principal principal) {
//Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        if (principal != null){
            model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        }
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "about";
    }

    private long getPageCountCategory(String name) {
        long totalCount = categoryService.findByNameEssence(name).getProductList().size();
        var pageCount = (totalCount / COUNT_PAGE_PRODUCTS) + ((totalCount % COUNT_PAGE_PRODUCTS > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }

    private long getPageCountAllProducts() {
        long totalCount = productService.count();
        var pageCount = (totalCount / COUNT_PAGE_ALL) + ((totalCount % COUNT_PAGE_ALL > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }

    private long getPageCountBySearch(String name) {
        long totalCount = productService.countSearchProduct(name);
        var pageCount = (totalCount / COUNT_PAGE_ALL) + ((totalCount % COUNT_PAGE_ALL > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }
}
