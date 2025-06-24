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

    @Override
    public SaveResponse savePayment(SavePaymentRequest paymentRequest) throws MPException, MPApiException {
        BookingEntity bookingFound = bookingRepository.findById(paymentRequest.bookingId()).orElse(null);
        if (bookingFound == null) {
            throw new NoExistException("La reserva no existe");
        }
        System.out.println("La reserva con id: " + paymentRequest.bookingId() + " EXISTE.");

        MercadoPagoConfig.setAccessToken("APP_USR-7625381313308330-062000-881e810befa67467c5382daff4cac8ae-2509326300");

        try {
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
                    .externalReference(paymentRequest.bookingId().toString())
                    .notificationUrl("https://df34-181-237-95-103.ngrok-free.app/api/v1/mercadopago/webhook?source_news=webhooks")

//                    .autoReturn("approved")
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

        } catch (MPApiException e) {
            System.out.println("🛑 API ERROR BODY:");
            System.out.println(e.getApiResponse().getContent());

            throw new RuntimeException("Error al crear preferencia de pago con Mercado Pago");
        }
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
}
