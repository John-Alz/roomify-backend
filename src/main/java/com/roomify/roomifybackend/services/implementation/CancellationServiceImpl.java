package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.CancellationEntity;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.CancellationRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.CancellationMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.ICancellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CancellationServiceImpl implements ICancellationService {

    private final CancellationRepository cancellationRepository;
    private final BookingRepository bookingRepository;
    private final CancellationMapper cancellationMapper;

    @Override
    public SaveResponse saveCancellation(SaveCancellationRequest cancellationRequest) {
        BookingEntity bookingFound = bookingRepository.findById(cancellationRequest.bookingId()).orElse(null);
        if (bookingFound == null) {
            throw new NoExistException("Esa reserva no existe.");
        }
        CancellationEntity cancellation = cancellationMapper.toCancellationEntity(cancellationRequest, bookingFound);
        cancellationRepository.save(cancellation);
        return new SaveResponse("Reserva cancelada", LocalDate.now());
    }
}
