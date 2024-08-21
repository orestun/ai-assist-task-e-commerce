package org.epam.aiassisthightask1ecommerce.mapper;

import org.epam.aiassisthightask1ecommerce.dto.UserDTO;
import org.epam.aiassisthightask1ecommerce.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void testToDTO() {
        User user = User.builder()
                .id(1L)
                .username("testUser")
                .email("test@example.com")
                .address("123 Test St")
                .build();

        UserDTO userDTO = userMapper.toDTO(user);

        assertNotNull(userDTO);
        assertEquals(1L, userDTO.getId());
        assertEquals("testUser", userDTO.getUsername());
        assertEquals("test@example.com", userDTO.getEmail());
        assertEquals("123 Test St", userDTO.getAddress());
    }

    @Test
    void testToEntity() {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .username("testUser")
                .email("test@example.com")
                .address("123 Test St")
                .build();

        User user = userMapper.toEntity(userDTO);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("123 Test St", user.getAddress());
    }
}