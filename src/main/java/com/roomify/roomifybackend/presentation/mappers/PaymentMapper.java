package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.PaymentEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveAmenityRequest;
import com.roomify.roomifybackend.presentation.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

//    public PaymentEntity toEntity(SaveAmenityRequest)

    public PaymentResponse toResponse(PaymentEntity paymentEntity) {
        return new PaymentResponse(
                paymentEntity.getId(),
                paymentEntity.getBooking(),
                paymentEntity.getPreference_id(),
                paymentEntity.getStatus(),
                paymentEntity.getAmount(),
                paymentEntity.getCreated_at()
        );
    }

    public List<PaymentResponse> toListResponse(List<PaymentEntity> paymentEntities) {
        return paymentEntities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}
