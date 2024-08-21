package org.epam.aiassisthightask1ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PaymentDTO {
    private Long id;
    private Long orderId;
    private LocalDate paymentDate;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;

    // Getters and Setters
}