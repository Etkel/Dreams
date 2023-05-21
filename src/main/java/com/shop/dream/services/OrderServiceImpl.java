package com.shop.dream.services;

import com.shop.dream.models.CartItem;
import com.shop.dream.models.Order;
import com.shop.dream.models.Persona;
import com.shop.dream.models.enums.OrderStatus;
import com.shop.dream.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PersonaService personaService;


    public Long parsePhone(String text) {
        Long phone = null;
        try {
            phone = Long.parseLong(text);
            if (phone < 1_000_000_000L || phone > 9_999_999_999L) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return phone;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findOrdersByEmail(String email, Pageable pageable) {
        return orderRepository.findOrdersByEmail(email, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Long ordersCountByMail(String email) {
        return orderRepository.ordersCountByMail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return orderRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Long countUncheckedOrders() {
        return orderRepository.countOrdersByStatus(OrderStatus.CREATED);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long orderId, OrderStatus status) {
        if (orderRepository.findById(orderId).isPresent()) {
            Order order = orderRepository.findById(orderId).get();
            order.setStatus(status);
        }
    }

    @Override
    public Long parsePostcode(String text) {
        Long postcode = null;
        try {
            postcode = Long.parseLong(text);
            if (postcode < 10_000L || postcode > 99_999L) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return postcode;
    }

    @Override
    public String makeAddress(String city, String streetAddress, String postcode) {
        return "City: " + city + "; Street address: " + streetAddress + "; Postcode: " + postcode + ".";
    }

    @Override
    public String emailBody() {
        return "Hello, \n" +
                "We would like to thank you for your order in our store. We appreciate your trust and\n" +
                "therefore strive to provide you with high-quality services.\n\n" +
                "You can view the details of your order on our website in the \"Your Orders\" section. If you\n" +
                "have any questions or comments regarding your order, please do not hesitate to contact us.\n\n" +
                "We would also like to inform you that our manager will contact you shortly to confirm the\n" +
                "order and clarify the delivery details.\n\n" +
                "Once again, thank you for your order and we hope for further cooperation.\n\n" +
                "Best regards,\n\n" +
                "Dream Shop";
    }

    @Override
    @Transactional
    public Order saveAndClear(Principal principal, String comments, Long phone, String address, BigDecimal total) {
        Persona persona = personaService.findByEmailEssence(principal.getName());
        List<CartItem> orderCartItems = new ArrayList<>();

        persona.getCart().getCartItems().forEach(cartItem -> {
            cartItem.setCart(null);
            orderCartItems.add(cartItem);
        });

        Order order = Order.builder()
                .persona(persona)
                .telephone(phone)
                .comments(comments)
                .address(address)
                .totalSum(total)
                .cartItems(orderCartItems)
                .status(OrderStatus.CREATED)
                .build();

        orderCartItems.forEach(cartItem -> cartItem.setOrder(order));
        orderRepository.save(order);

        return order;
    }
}
