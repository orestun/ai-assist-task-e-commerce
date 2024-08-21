package org.epam.aiassisthightask1ecommerce.mapper;

import org.epam.aiassisthightask1ecommerce.dto.PaymentDTO;
import org.epam.aiassisthightask1ecommerce.entity.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMapperTest {
    private PaymentMapper paymentMapper;

    @BeforeEach
    void setUp() {
        paymentMapper = new PaymentMapper();
    }

    @Test
    void testToDTO() {
        Payment payment = Payment.builder()
                .id(1L)
                .orderId(2L)
                .paymentDate(LocalDate.of(2024, 8, 21))
                .amount(199.99)
                .paymentMethod("Credit Card")
                .paymentStatus("Completed")
                .build();

        PaymentDTO paymentDTO = paymentMapper.toDTO(payment);

        assertNotNull(paymentDTO);
        assertEquals(1L, paymentDTO.getId());
        assertEquals(2L, paymentDTO.getOrderId());
        assertEquals(LocalDate.of(2024, 8, 21), paymentDTO.getPaymentDate());
        assertEquals(199.99, paymentDTO.getAmount());
        assertEquals("Credit Card", paymentDTO.getPaymentMethod());
        assertEquals("Completed", paymentDTO.getPaymentStatus());
    }

    @Test
    void testToEntity() {
        PaymentDTO paymentDTO = PaymentDTO.builder()
                .id(1L)
                .orderId(2L)
                .paymentDate(LocalDate.of(2024, 8, 21))
                .amount(199.99)
                .paymentMethod("Credit Card")
                .paymentStatus("Completed")
                .build();

        Payment payment = paymentMapper.toEntity(paymentDTO);

        assertNotNull(payment);
        assertEquals(1L, payment.getId());
        assertEquals(2L, payment.getOrderId());
        assertEquals(LocalDate.of(2024, 8, 21), payment.getPaymentDate());
        assertEquals(199.99, payment.getAmount());
        assertEquals("Credit Card", payment.getPaymentMethod());
        assertEquals("Completed", payment.getPaymentStatus());
    }
}