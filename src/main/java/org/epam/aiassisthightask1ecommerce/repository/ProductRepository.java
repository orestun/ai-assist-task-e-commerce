package org.epam.aiassisthightask1ecommerce.repository;

import org.epam.aiassisthightask1ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can define custom queries here if needed
}
