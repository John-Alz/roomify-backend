package com.roomify.roomifybackend.presentation.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.roomify.roomifybackend.persistence.entity.FiltersPayments;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SavePaymentRequest;
import com.roomify.roomifybackend.presentation.dto.response.PaymentResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.services.interfaces.IPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    
    private final IPaymentService paymentService;
    
    public  PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @PostMapping
    public ResponseEntity<SaveResponse> savePayment(@RequestBody SavePaymentRequest paymentRequest) throws MPException, MPApiException {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.savePayment(paymentRequest));
    }

    @GetMapping
    public ResponseEntity<PageResult<PaymentResponse>> getPayments(Integer page, Integer size, boolean orderAsc, @ModelAttribute FiltersPayments filtersPayments) {
        PageResult<PaymentResponse> paymentResponsePageResult = paymentService.getPayments(page, size, orderAsc, filtersPayments);
        return ResponseEntity.ok(paymentResponsePageResult);
    }

    @GetMapping("{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long paymentId) {
        PaymentResponse paymentResponse = paymentService.getPayment(paymentId);
        return ResponseEntity.ok(paymentResponse);
    }
    
}
