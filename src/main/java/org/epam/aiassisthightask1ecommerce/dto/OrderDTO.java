package org.epam.aiassisthightask1ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private double totalAmount;
    private String status;
    private List<OrderItemDTO> orderItems;

    // Getters and Setters
}