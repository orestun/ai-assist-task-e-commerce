package org.epam.aiassisthightask1ecommerce.repository;

import org.epam.aiassisthightask1ecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // You can define custom queries here if needed
}