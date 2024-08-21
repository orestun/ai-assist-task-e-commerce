package org.epam.aiassisthightask1ecommerce.mapper;

import org.epam.aiassisthightask1ecommerce.dto.OrderDTO;
import org.epam.aiassisthightask1ecommerce.dto.OrderItemDTO;
import org.epam.aiassisthightask1ecommerce.entity.Order;
import org.epam.aiassisthightask1ecommerce.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }
        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .orderItems(order.getOrderItems().stream()
                        .map(orderItem -> OrderItemDTO.builder()
                                .id(orderItem.getId())
                                .orderId(orderItem.getOrderId())
                                .productId(orderItem.getProductId())
                                .quantity(orderItem.getQuantity())
                                .price(orderItem.getPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        Order order = new Order();
        order.setId(dto.getId());
        order.setUserId(dto.getUserId());
        order.setOrderDate(dto.getOrderDate());
        order.setTotalAmount(dto.getTotalAmount());
        order.setStatus(dto.getStatus());
        order.setOrderItems(dto.getOrderItems().stream()
                .map(orderItemDTO -> OrderItem.builder()
                        .id(orderItemDTO.getId())
                        .orderId(orderItemDTO.getOrderId())
                        .productId(orderItemDTO.getProductId())
                        .quantity(orderItemDTO.getQuantity())
                        .price(orderItemDTO.getPrice())
                        .build())
                .collect(Collectors.toList()));
        return order;
    }
}