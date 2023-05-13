package com.shop.dream.services;

import com.shop.dream.models.Order;
import com.shop.dream.models.enums.OrderStatus;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface OrderService {
    Order saveAndClear(Principal principal, String comments, Long phone, String address, BigDecimal total);
    Long parsePhone(String text);
    List<Order> findOrdersByEmail(String email, Pageable pageable);
    Long ordersCountByMail(String email);
    List<Order> findAll(Pageable pageable);
    Long Count();
    Long countUncheckedOrders();
    void updateOrderStatus(Long orderId, OrderStatus status);
    Long parsePostcode(String text);
    String makeAddress(String city, String streetAddress, String postcode);
    String emailBody();
}
