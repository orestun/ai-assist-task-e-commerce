package org.epam.aiassisthightask1ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;
    private double price;

    // Getters and Setters
}