package org.epam.aiassisthightask1ecommerce.repository;

import org.epam.aiassisthightask1ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // You can define custom queries here if needed
}