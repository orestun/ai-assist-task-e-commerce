package org.epam.aiassisthightask1ecommerce.mapper;

import org.epam.aiassisthightask1ecommerce.dto.OrderDTO;
import org.epam.aiassisthightask1ecommerce.dto.OrderItemDTO;
import org.epam.aiassisthightask1ecommerce.entity.Order;
import org.epam.aiassisthightask1ecommerce.entity.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        orderMapper = new OrderMapper();
    }

    @Test
    void testToDTO() {
        Order order = Order.builder()
                .id(1L)
                .userId(2L)
                .orderDate(LocalDate.of(2024, 8, 21))
                .totalAmount(199.99)
                .status("Completed")
                .orderItems(Collections.singletonList(
                        OrderItem.builder()
                                .id(1L)
                                .orderId(1L)
                                .productId(3L)
                                .quantity(2)
                                .price(99.99)
                                .build()))
                .build();

        OrderDTO orderDTO = orderMapper.toDTO(order);

        assertNotNull(orderDTO);
        assertEquals(1L, orderDTO.getId());
        assertEquals(2L, orderDTO.getUserId());
        assertEquals(LocalDate.of(2024, 8, 21), orderDTO.getOrderDate());
        assertEquals(199.99, orderDTO.getTotalAmount());
        assertEquals("Completed", orderDTO.getStatus());
        assertFalse(orderDTO.getOrderItems().isEmpty());
        assertEquals(1L, orderDTO.getOrderItems().get(0).getId());
    }

    @Test
    void testToEntity() {
        OrderDTO orderDTO = OrderDTO.builder()
                .id(1L)
                .userId(2L)
                .orderDate(LocalDate.of(2024, 8, 21))
                .totalAmount(199.99)
                .status("Completed")
                .orderItems(Collections.singletonList(
                        OrderItemDTO.builder()
                                .id(1L)
                                .orderId(1L)
                                .productId(3L)
                                .quantity(2)
                                .price(99.99)
                                .build()))
                .build();

        Order order = orderMapper.toEntity(orderDTO);

        assertNotNull(order);
        assertEquals(1L, order.getId());
        assertEquals(2L, order.getUserId());
        assertEquals(LocalDate.of(2024, 8, 21), order.getOrderDate());
        assertEquals(199.99, order.getTotalAmount());
        assertEquals("Completed", order.getStatus());
        assertFalse(order.getOrderItems().isEmpty());
        assertEquals(1L, order.getOrderItems().get(0).getId());
    }
}
