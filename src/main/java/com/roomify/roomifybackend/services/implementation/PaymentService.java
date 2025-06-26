package com.roomify.roomifybackend.services.implementation;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.PaymentRepository;
import com.roomify.roomifybackend.presentation.dto.request.SavePaymentRequest;
import com.roomify.roomifybackend.presentation.dto.response.PaymentResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.PaymentMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.IPaymentService;
import com.roomify.roomifybackend.specification.SearchPaymentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PaymentMapper paymentMapper;

    @Value("${access-token-mcdp}")
    private String accessToken;

    @Override
    public SaveResponse savePayment(SavePaymentRequest paymentRequest) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(accessToken);

        BookingEntity bookingFound = bookingRepository.findById(paymentRequest.bookingId()).orElse(null);
        if (bookingFound == null) {
            throw new NoExistException("La reserva no existe");
        }
        System.out.println("La reserva con id: " + paymentRequest.bookingId() + " EXISTE.");

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id("roomify-" + bookingFound.getId())
                    .title("Reserva en habitación: " + bookingFound.getRoomType().getName() + " X" + bookingFound.getNumberOfRoom())
                    .quantity(1)
                    .currencyId("COP")
                    .unitPrice(bookingFound.getTotalPrice().setScale(0))
                    .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PaymentEntity paymentEntity = PaymentEntity.builder()
                    .booking(bookingFound)
                    .preference_id(preference.getId())
                    .status(PaymentStatus.PENDING)
                    .amount(bookingFound.getTotalPrice())
                    .created_at(LocalDateTime.now())
                    .build();
            paymentRepository.save(paymentEntity);
            return new SaveResponse(preference.getSandboxInitPoint(), LocalDate.now());
    }

    @Override
    public PageResult<PaymentResponse> getPayments(Integer page, Integer size, boolean orderAsc, FiltersPayments filtersPayments) {
        Pageable paging = PageRequest.of(page, size);
        SearchPaymentSpecification spec = new SearchPaymentSpecification(
                filtersPayments.numberBooking(),
                filtersPayments.dateFromPayment(),
                filtersPayments.dateToPayment(),
                filtersPayments.priceMin(),
                filtersPayments.roomTypeId(),
                filtersPayments.status()
        );
        Page<PaymentEntity> paymentPage = paymentRepository.findAll(spec, paging);
        List<PaymentResponse> paymentResponseList = paymentMapper.toListResponse(paymentPage.getContent());
        return new PageResult<>(
                paymentResponseList,
                paymentPage.getNumber(),
                paymentPage.getSize(),
                paymentPage.getTotalPages(),
                paymentPage.getTotalElements()
        );
    }

    @Override
    public PaymentResponse getPayment(Long paymentId) {
        PaymentEntity paymentFound = paymentRepository.findById(paymentId).orElse(null);
        if (paymentFound == null) {
            throw new NoExistException("E l pago con este id no existe.");
        }
        return paymentMapper.toResponse(paymentFound);
    }
}
