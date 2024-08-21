package org.epam.aiassisthightask1ecommerce.mapper;

import org.epam.aiassisthightask1ecommerce.dto.OrderItemDTO;
import org.epam.aiassisthightask1ecommerce.entity.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemMapperTest {
    private OrderItemMapper orderItemMapper;

    @BeforeEach
    void setUp() {
        orderItemMapper = new OrderItemMapper();
    }

    @Test
    void testToDTO() {
        OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .orderId(2L)
                .productId(3L)
                .quantity(2)
                .price(99.99)
                .build();

        OrderItemDTO orderItemDTO = orderItemMapper.toDTO(orderItem);

        assertNotNull(orderItemDTO);
        assertEquals(1L, orderItemDTO.getId());
        assertEquals(2L, orderItemDTO.getOrderId());
        assertEquals(3L, orderItemDTO.getProductId());
        assertEquals(2, orderItemDTO.getQuantity());
        assertEquals(99.99, orderItemDTO.getPrice());
    }

    @Test
    void testToEntity() {
        OrderItemDTO orderItemDTO = OrderItemDTO.builder()
                .id(1L)
                .orderId(2L)
                .productId(3L)
                .quantity(2)
                .price(99.99)
                .build();

        OrderItem orderItem = orderItemMapper.toEntity(orderItemDTO);

        assertNotNull(orderItem);
        assertEquals(1L, orderItem.getId());
        assertEquals(2L, orderItem.getOrderId());
        assertEquals(3L, orderItem.getProductId());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(99.99, orderItem.getPrice());
    }
}