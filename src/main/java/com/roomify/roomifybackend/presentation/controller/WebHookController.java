package com.roomify.roomifybackend.presentation.controller;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.services.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/mercadopago/webhook")
@RequiredArgsConstructor
public class WebHookController {

    private final IBookingService bookingService;

    @PostMapping
    public ResponseEntity<Void> handleWebhook(@RequestBody Map<String, Object> payload) throws MPException, MPApiException, InterruptedException {

        System.out.println("🔔 Webhook recibido: " + payload);
        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        Long paymentId = Long.valueOf(data.get("id").toString());

        Thread.sleep(2000);
        
        PaymentClient client = new PaymentClient();
        Payment payment = client.get(paymentId);

        String status = payment.getStatus();
        String externalReference = payment.getExternalReference();

        bookingService.updatePaymentStatus(Long.valueOf(externalReference), status);

        return ResponseEntity.ok().build();
    }
}
