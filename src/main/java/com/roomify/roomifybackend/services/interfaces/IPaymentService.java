package com.roomify.roomifybackend.services.interfaces;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.roomify.roomifybackend.presentation.dto.request.SavePaymentRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IPaymentService {

    SaveResponse savePayment(SavePaymentRequest paymentRequest) throws MPException, MPApiException;

}
