package com.shop.dream.contollers;

import com.shop.dream.dto.CartDTO;
import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.services.CartItemsService;
import com.shop.dream.services.CategoryService;
import com.shop.dream.services.OrderService;
import com.shop.dream.services.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartItemsService cartItemsService;
    private final CategoryService categoryService;
    private final PersonaService personaService;
    private final OrderService orderService;

    @PostMapping("/user/cart/{productId}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long productId, Principal principal,
                                                @RequestParam(required = false, defaultValue = "1", name = "amount")
                                                Integer amount) {
        cartItemsService.addItemToCart(productId, amount, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/cart")
    public String theCart(Model model, Principal principal) {
        model.addAttribute("cart", personaService.findByEmail(principal.getName()).getCart());
//Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "cart";
    }

    @PostMapping("/user/cart-upd")
    public String update(@ModelAttribute("cart") CartDTO dto,
                         @ModelAttribute("total") BigDecimal total) {
        if (dto.getCartItems() == null) {
            return "redirect:/user/cart?emptycart";
        }
        cartItemsService.updateItems(dto);
        return "redirect:/user/order?total=" + total;
    }

    @GetMapping("/user/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId) {
        cartItemsService.delete(itemId);
        return "redirect:/user/cart";
    }

}
