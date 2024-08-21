package org.epam.aiassisthightask1ecommerce.service;

import org.epam.aiassisthightask1ecommerce.dto.UserDTO;
import org.epam.aiassisthightask1ecommerce.entity.User;
import org.epam.aiassisthightask1ecommerce.mapper.UserMapper;
import org.epam.aiassisthightask1ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        UserDTO userDTO = UserDTO.builder().username("testUser").email("test@example.com").build();
        User user = User.builder().username("testUser").email("test@example.com").build();
        User savedUser = User.builder().id(1L).username("testUser").email("test@example.com").build();
        UserDTO savedUserDTO = UserDTO.builder().id(1L).username("testUser").email("test@example.com").build();

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toDTO(savedUser)).thenReturn(savedUserDTO);

        UserDTO result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testUser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void testGetUserById() {
        User user = User.builder().id(1L).username("testUser").email("test@example.com").build();
        UserDTO userDTO = UserDTO.builder().id(1L).username("testUser").email("test@example.com").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void testGetAllUsers() {
        User user1 = User.builder().id(1L).username("user1").email("user1@example.com").build();
        User user2 = User.builder().id(2L).username("user2").email("user2@example.com").build();
        UserDTO userDTO1 = UserDTO.builder().id(1L).username("user1").email("user1@example.com").build();
        UserDTO userDTO2 = UserDTO.builder().id(2L).username("user2").email("user2@example.com").build();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        when(userMapper.toDTO(user1)).thenReturn(userDTO1);
        when(userMapper.toDTO(user2)).thenReturn(userDTO2);

        var result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    void testUpdateUser() {
        UserDTO userDTO = UserDTO.builder().username("updatedUser").email("updated@example.com").build();
        User user = User.builder().id(1L).username("updatedUser").email("updated@example.com").build();
        UserDTO updatedUserDTO = UserDTO.builder().id(1L).username("updatedUser").email("updated@example.com").build();

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(updatedUserDTO);

        UserDTO result = userService.updateUser(1L, userDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("updatedUser", result.getUsername());
    }

    @Test
    void testUpdateUserNotFound() {
        UserDTO userDTO = UserDTO.builder().username("updatedUser").email("updated@example.com").build();

        when(userRepository.existsById(1L)).thenReturn(false);

        UserDTO result = userService.updateUser(1L, userDTO);

        assertNull(result);
    }

    @Test
    void testDeleteUser() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.existsById(1L)).thenReturn(false);

        boolean result = userService.deleteUser(1L);

        assertFalse(result);
        verify(userRepository, never()).deleteById(1L);
    }
}