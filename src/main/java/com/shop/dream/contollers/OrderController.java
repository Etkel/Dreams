package com.shop.dream.contollers;

import com.shop.dream.components.EmailSender;
import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.models.CartItem;
import com.shop.dream.models.Order;
import com.shop.dream.models.enums.OrderStatus;
import com.shop.dream.services.CategoryService;
import com.shop.dream.services.OrderService;
import com.shop.dream.services.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final PersonaService personaService;
    private final CategoryService categoryService;
    private static final Integer COUNT_PAGE_ORDER = 12;
    private final EmailSender emailSender;

    @GetMapping("/user/order")
    public String order(Model model, @RequestParam BigDecimal total, Principal principal) {
        model.addAttribute("total", total);
        List<CartItem> cartItemList = personaService.findByEmail(principal.getName()).getCart().getCartItems();
        if (cartItemList.size() == 0) {
            return "redirect:/user/cart";
        } else model.addAttribute("cartItems", cartItemList);
        //Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "order";
    }

    @PostMapping("/user/order")
    public String makeOrder(@ModelAttribute("total") BigDecimal total,
                            @RequestParam String address, @RequestParam String comments,
                            @RequestParam String postcode, @RequestParam String city,
                            @RequestParam String phone, Principal principal) {
        if (orderService.parsePhone(phone) == null) {
            return "redirect:/user/order?total=" + total + "&invalid";
        }
        if (orderService.parsePostcode(postcode) == null) {
            return "redirect:/user/order?total=" + total + "&invalidcode";
        }
        address = orderService.makeAddress(city, address, postcode);
        Long telephone = orderService.parsePhone(phone);
        Order order = orderService.saveAndClear(principal, comments, telephone, address, total);
        return "redirect:/user/order/success/" + order.getId();
    }

    @GetMapping("/user/order/success/{orderId}")
    public String orderDone(Model model, Principal principal,
                            @PathVariable Long orderId) {
        emailSender.sendEmail(principal.getName(), "Order#" + orderId + " at Dream Shop",
                orderService.emailBody());
        //Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "success";
    }

    @GetMapping("/user/orders")
    public String orders(Model model, Principal principal,
                         @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<Order> orders = orderService.findOrdersByEmail(principal.getName(),
                PageRequest.of(page, COUNT_PAGE_ORDER, Sort.Direction.DESC, "id"));
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("allPages", getPageCountOrders(principal.getName()));
        //Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "order-list";
    }

    @GetMapping("/admin/orders")
    public String ordersAll(Model model, Principal principal,
                            @RequestParam(required = false, defaultValue = "0") Integer page) {
        List<Order> orders = orderService.findAll(
                PageRequest.of(page, COUNT_PAGE_ORDER, Sort.Direction.DESC, "id"));
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("allPages", getPageCountOrders());
        //Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        return "order-list-admin";
    }

    @PostMapping("/admin/order/{orderId}")
    public ResponseEntity<String> addItemToCart(@PathVariable Long orderId,
                                                @RequestParam(name = "status")
                                                OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok().build();
    }

    private long getPageCountOrders(String email) {
        long totalCount = orderService.ordersCountByMail(email);
        var pageCount = (totalCount / COUNT_PAGE_ORDER) + ((totalCount % COUNT_PAGE_ORDER > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }

    private long getPageCountOrders() {
        long totalCount = orderService.Count();
        var pageCount = (totalCount / COUNT_PAGE_ORDER) + ((totalCount % COUNT_PAGE_ORDER > 0) ? 1 : 0);
        return pageCount == 0 ? 1 : pageCount;
    }
}
