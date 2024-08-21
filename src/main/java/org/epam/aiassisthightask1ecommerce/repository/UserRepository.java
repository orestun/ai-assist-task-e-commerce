package org.epam.aiassisthightask1ecommerce.repository;

import org.epam.aiassisthightask1ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can define custom queries here if needed
}