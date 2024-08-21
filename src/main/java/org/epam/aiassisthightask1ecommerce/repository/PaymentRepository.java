package org.epam.aiassisthightask1ecommerce.repository;

import org.epam.aiassisthightask1ecommerce.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // You can define custom queries here if needed
}