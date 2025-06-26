package com.roomify.roomifybackend.presentation.controller;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.roomify.roomifybackend.services.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mercadopago/webhook")
@RequiredArgsConstructor
public class WebHookController {

    private final IBookingService bookingService;

    @PostMapping
    public void handleWebhook(@RequestParam("id") Long paymentId, @RequestParam("topic") String topic ) throws MPException, MPApiException {
        if(!topic.equals("payment")) return;

        PaymentClient client = new PaymentClient();
        Payment payment = client.get(paymentId);

        String preferenceId = payment.getExternalReference();
        String status = payment.getStatus(); // approved, rejected, pending, etc.
        System.out.println(preferenceId);
        System.out.println(status);
    }

}
