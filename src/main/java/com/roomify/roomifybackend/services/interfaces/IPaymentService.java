package com.roomify.roomifybackend.services.interfaces;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.roomify.roomifybackend.persistence.entity.FiltersPayments;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SavePaymentRequest;
import com.roomify.roomifybackend.presentation.dto.response.PaymentResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IPaymentService {

    SaveResponse savePayment(SavePaymentRequest paymentRequest) throws MPException, MPApiException;

    PageResult<PaymentResponse> getPayments(Integer page, Integer size, boolean orderAsc, FiltersPayments filtersPayments);

}
