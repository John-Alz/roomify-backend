package com.roomify.roomifybackend.presentation.controller;

import com.mercadopago.MercadoPagoConfig;
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
    public ResponseEntity<Void> handleWebhook(@RequestBody Map<String, Object> payload) throws MPException, InterruptedException {
        System.out.println("🔔 Webhook recibido: " + payload);

        Map<String, Object> data = (Map<String, Object>) payload.get("data");
        if (data == null || !data.containsKey("id")) {
            return ResponseEntity.badRequest().build();
        }

        Long paymentId = Long.valueOf(data.get("id").toString());
        String action = (String) payload.get("action");
        if (!"payment.updated".equals(action)) {
            return ResponseEntity.ok().build(); // Ignora eventos que no son actualización
        }

        Thread.sleep(2000); // opcional, si quieres dar tiempo a que se procese el pago

        MercadoPagoConfig.setAccessToken("APP_USR-7625381313308330-062000-881e810befa67467c5382daff4cac8ae-2509326300");

        try {
            PaymentClient client = new PaymentClient();
            Payment payment = client.get(paymentId);

            if (payment.getStatus() == null || payment.getStatus().equals("pending")) {
                System.out.println("⏳ Pago aún no aprobado: " + payment.getStatus());
                return ResponseEntity.ok().build();
            }

            String status = payment.getStatus();
            String externalReference = payment.getExternalReference();

            System.out.println(status);
            System.out.println(externalReference);
            bookingService.updatePaymentStatus(Long.valueOf(externalReference), status);

        } catch (MPApiException e) {
            System.err.println("❌ MercadoPago error: " + e.getApiResponse().getContent());
        }

        return ResponseEntity.ok().build();
    }

}
