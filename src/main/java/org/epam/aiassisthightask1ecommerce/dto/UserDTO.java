package org.epam.aiassisthightask1ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String address;

    // Getters and Setters
}