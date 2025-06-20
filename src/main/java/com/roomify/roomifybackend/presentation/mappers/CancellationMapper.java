package com.roomify.roomifybackend.presentation.mappers;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.CancellationEntity;
import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.CancellationResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CancellationMapper {

    public CancellationEntity toCancellationEntity(SaveCancellationRequest cancellationRequest, BookingEntity booking) {
        return CancellationEntity.builder()
                .booking(booking)
                .dateOfCancellation(LocalDate.now())
                .reasonForCancellation(cancellationRequest.reasonForCancellation())
                .build();
    }

    public CancellationResponse toResponse(CancellationEntity cancellation, BookingResponse bookingResponse) {
        return new CancellationResponse(
                cancellation.getId(),
                bookingResponse,
                cancellation.getDateOfCancellation(),
                cancellation.getReasonForCancellation()
        );
    }

}
