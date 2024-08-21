package org.epam.aiassisthightask1ecommerce.service;

import org.epam.aiassisthightask1ecommerce.dto.OrderDTO;
import org.epam.aiassisthightask1ecommerce.entity.Order;
import org.epam.aiassisthightask1ecommerce.mapper.OrderMapper;
import org.epam.aiassisthightask1ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    public OrderDTO getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(orderMapper::toDTO).orElse(null);
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDTO).toList();
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        if (!orderRepository.existsById(id)) {
            return null;
        }
        Order order = orderMapper.toEntity(orderDTO);
        order.setId(id);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toDTO(updatedOrder);
    }

    public boolean deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        orderRepository.deleteById(id);
        return true;
    }
}
