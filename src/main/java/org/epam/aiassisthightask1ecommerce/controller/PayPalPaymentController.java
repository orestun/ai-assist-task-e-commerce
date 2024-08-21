package org.epam.aiassisthightask1ecommerce.controller;

import org.epam.aiassisthightask1ecommerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PayPalPaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<String> createPayment(@RequestParam("total") double total,
                                                @RequestParam("currency") String currency,
                                                @RequestParam("method") String method,
                                                @RequestParam("intent") String intent,
                                                @RequestParam("description") String description,
                                                @RequestParam("cancelUrl") String cancelUrl,
                                                @RequestParam("successUrl") String successUrl) {
        try {
            String payment = paymentService.createPayment(total, currency, method, intent, description, cancelUrl, successUrl);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating payment: " + e.getMessage());
        }
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executePayment(@RequestParam("paymentId") String paymentId,
                                                 @RequestParam("payerId") String payerId) {
        try {
            String payment = paymentService.executePayment(paymentId, payerId);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error executing payment: " + e.getMessage());
        }
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancelPayment() {
        return ResponseEntity.ok("Payment cancelled.");
    }

    @GetMapping("/success")
    public ResponseEntity<String> successPayment(@RequestParam("paymentId") String paymentId,
                                                 @RequestParam("payerId") String payerId) {
        try {
            String payment = paymentService.executePayment(paymentId, payerId);
            return ResponseEntity.ok("Payment successful: " + payment);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error executing payment: " + e.getMessage());
        }
    }
}