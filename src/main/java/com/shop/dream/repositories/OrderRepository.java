package com.shop.dream.repositories;

import com.shop.dream.models.Order;
import com.shop.dream.models.enums.OrderStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.persona.email=:email")
    List<Order> findOrdersByEmail(@Param(value = "email")String email, Pageable pageable);
    @Query("SELECT COUNT (o) FROM Order o WHERE o.persona.email=:email")
    Long ordersCountByMail(@Param(value = "email")String email);
    Long countOrdersByStatus(OrderStatus status);
}
