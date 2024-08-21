package org.epam.aiassisthightask1ecommerce.controller;

import org.epam.aiassisthightask1ecommerce.dto.OrderDTO;
import org.epam.aiassisthightask1ecommerce.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testCreateOrder() throws Exception {
        OrderDTO orderDTO = OrderDTO.builder().status("Order Description").build();
        OrderDTO createdOrderDTO = OrderDTO.builder().id(1L).status("Order Description").build();

        when(orderService.createOrder(orderDTO)).thenReturn(createdOrderDTO);

        mockMvc.perform(post("/api/orders")
                        .contentType("application/json")
                        .content("{\"status\":\"Order Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("Order Description"));
    }

    @Test
    void testGetOrderById() throws Exception {
        OrderDTO orderDTO = OrderDTO.builder().id(1L).status("Order Description").build();

        when(orderService.getOrderById(1L)).thenReturn(orderDTO);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("Order Description"));
    }

    @Test
    void testGetOrderByIdNotFound() throws Exception {
        when(orderService.getOrderById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllOrders() throws Exception {
        OrderDTO orderDTO1 = OrderDTO.builder().id(1L).status("Order 1").build();
        OrderDTO orderDTO2 = OrderDTO.builder().id(2L).status("Order 2").build();
        List<OrderDTO> orders = Arrays.asList(orderDTO1, orderDTO2);

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].status").value("Order 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].status").value("Order 2"));
    }

    @Test
    void testUpdateOrder() throws Exception {
        OrderDTO orderDTO = OrderDTO.builder().status("Updated Description").build();
        OrderDTO updatedOrderDTO = OrderDTO.builder().id(1L).status("Updated Description").build();

        when(orderService.updateOrder(1L, orderDTO)).thenReturn(updatedOrderDTO);

        mockMvc.perform(put("/api/orders/1")
                        .contentType("application/json")
                        .content("{\"status\":\"Updated Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("Updated Description"));
    }

    @Test
    void testUpdateOrderNotFound() throws Exception {
        OrderDTO orderDTO = OrderDTO.builder().status("Updated Description").build();

        when(orderService.updateOrder(1L, orderDTO)).thenReturn(null);

        mockMvc.perform(put("/api/orders/1")
                        .contentType("application/json")
                        .content("{\"status\":\"Updated Description\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteOrder() throws Exception {
        when(orderService.deleteOrder(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteOrderNotFound() throws Exception {
        when(orderService.deleteOrder(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNotFound());
    }
}