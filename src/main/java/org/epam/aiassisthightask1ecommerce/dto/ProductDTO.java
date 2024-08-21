package org.epam.aiassisthightask1ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;

    // Getters and Setters
}