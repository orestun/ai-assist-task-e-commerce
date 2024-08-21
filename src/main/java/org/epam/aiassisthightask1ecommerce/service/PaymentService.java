package org.epam.aiassisthightask1ecommerce.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final PayPalAuthService payPalAuthService;
    private final RestTemplate restTemplate;

    @Value("${paypal.client.secret}")
    private String paypalClientSecret;

    @Value("${paypal.client.id}")
    private String paypalClientId;

    public PaymentService(PayPalAuthService payPalAuthService) {
        this.payPalAuthService = payPalAuthService;
        this.restTemplate = new RestTemplate();
    }

    public String createPayment(double total, String currency, String method,
                                String intent, String description, String cancelUrl,
                                String successUrl) {
        String url = "https://api.sandbox.paypal.com/v1/payments/payment";

        HttpHeaders headers = new HttpHeaders();
        String token = payPalAuthService.getAccessToken(paypalClientId, paypalClientSecret);
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construct the request body
        String requestBody = "{"
                + "\"intent\":\"" + intent + "\","
                + "\"payer\":{"
                + "\"payment_method\":\"" + method + "\""
                + "},"
                + "\"transactions\":[{"
                + "\"amount\":{"
                + "\"total\":\"" + total + "\","
                + "\"currency\":\"" + currency + "\""
                + "},"
                + "\"description\":\"" + description + "\""
                + "}],"
                + "\"redirect_urls\":{"
                + "\"cancel_url\":\"" + cancelUrl + "\","
                + "\"return_url\":\"" + successUrl + "\""
                + "}"
                + "}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody(); // Return payment approval URL or ID
        } else {
            // Handle the error
            throw new RuntimeException("Failed to create payment: " + response.getStatusCode() + " " + response.getBody());
        }
    }

    public String executePayment(String paymentId, String payerId) {
        String url = "https://api.sandbox.paypal.com/v1/payments/payment/" + paymentId + "/execute";

        HttpHeaders headers = new HttpHeaders();
        String token = payPalAuthService.getAccessToken(paypalClientId, paypalClientSecret);
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construct the request body
        String requestBody = "{"
                + "\"payer_id\":\"" + payerId + "\""
                + "}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody(); // Return payment details
        } else {
            // Handle the error
            throw new RuntimeException("Failed to execute payment: " + response.getStatusCode() + " " + response.getBody());
        }
    }
}
