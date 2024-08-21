package org.epam.aiassisthightask1ecommerce.service;

import org.epam.aiassisthightask1ecommerce.dto.OrderDTO;
import org.epam.aiassisthightask1ecommerce.entity.Order;
import org.epam.aiassisthightask1ecommerce.mapper.OrderMapper;
import org.epam.aiassisthightask1ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        OrderDTO orderDTO = OrderDTO.builder().status("Order Description").build();
        Order order = Order.builder().status("Order Description").build();
        Order savedOrder = Order.builder().id(1L).status("Order Description").build();
        OrderDTO savedOrderDTO = OrderDTO.builder().id(1L).status("Order Description").build();

        when(orderMapper.toEntity(orderDTO)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(savedOrder);
        when(orderMapper.toDTO(savedOrder)).thenReturn(savedOrderDTO);

        OrderDTO result = orderService.createOrder(orderDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Order Description", result.getStatus());
    }

    @Test
    void testGetOrderById() {
        Order order = Order.builder().id(1L).status("Order Description").build();
        OrderDTO orderDTO = OrderDTO.builder().id(1L).status("Order Description").build();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderMapper.toDTO(order)).thenReturn(orderDTO);

        OrderDTO result = orderService.getOrderById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Order Description", result.getStatus());
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        OrderDTO result = orderService.getOrderById(1L);

        assertNull(result);
    }

    @Test
    void testGetAllOrders() {
        Order order1 = Order.builder().id(1L).status("Order 1").build();
        Order order2 = Order.builder().id(2L).status("Order 2").build();
        OrderDTO orderDTO1 = OrderDTO.builder().id(1L).status("Order 1").build();
        OrderDTO orderDTO2 = OrderDTO.builder().id(2L).status("Order 2").build();

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));
        when(orderMapper.toDTO(order1)).thenReturn(orderDTO1);
        when(orderMapper.toDTO(order2)).thenReturn(orderDTO2);

        var result = orderService.getAllOrders();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Order 1", result.get(0).getStatus());
        assertEquals("Order 2", result.get(1).getStatus());
    }

    @Test
    void testUpdateOrder() {
        OrderDTO orderDTO = OrderDTO.builder().status("Updated Description").build();
        Order order = Order.builder().id(1L).status("Updated Description").build();
        OrderDTO updatedOrderDTO = OrderDTO.builder().id(1L).status("Updated Description").build();

        when(orderRepository.existsById(1L)).thenReturn(true);
        when(orderMapper.toEntity(orderDTO)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toDTO(order)).thenReturn(updatedOrderDTO);

        OrderDTO result = orderService.updateOrder(1L, orderDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Description", result.getStatus());
    }

    @Test
    void testUpdateOrderNotFound() {
        OrderDTO orderDTO = OrderDTO.builder().status("Updated Description").build();

        when(orderRepository.existsById(1L)).thenReturn(false);

        OrderDTO result = orderService.updateOrder(1L, orderDTO);

        assertNull(result);
    }

    @Test
    void testDeleteOrder() {
        when(orderRepository.existsById(1L)).thenReturn(true);

        boolean result = orderService.deleteOrder(1L);

        assertTrue(result);
        verify(orderRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteOrderNotFound() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        boolean result = orderService.deleteOrder(1L);

        assertFalse(result);
        verify(orderRepository, never()).deleteById(1L);
    }
}