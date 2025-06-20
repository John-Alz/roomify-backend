package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import com.roomify.roomifybackend.persistence.entity.CancellationEntity;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.CancellationRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.CancellationResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.BookingMapper;
import com.roomify.roomifybackend.presentation.mappers.CancellationMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.ICancellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CancellationServiceImpl implements ICancellationService {

    private final CancellationRepository cancellationRepository;
    private final BookingRepository bookingRepository;
    private final CancellationMapper cancellationMapper;
    private final BookingMapper bookingMapper;

    @Override
    public SaveResponse saveCancellation(SaveCancellationRequest cancellationRequest) {
        BookingEntity bookingFound = bookingRepository.findById(cancellationRequest.bookingId()).orElse(null);
        if (bookingFound == null) {
            throw new NoExistException("Esa reserva no existe.");
        }
        CancellationEntity cancellation = cancellationMapper.toCancellationEntity(cancellationRequest, bookingFound);
        bookingFound.setStatus(BookingStatus.CANCELADA);
        bookingRepository.save(bookingFound);
        cancellationRepository.save(cancellation);
        return new SaveResponse("Reserva cancelada", LocalDate.now());
    }

    @Override
    public PageResult<CancellationResponse> getAllCancellations(Integer page, Integer size, boolean orderAsc) {
        Pageable paging = PageRequest.of(page, size);
        Page<CancellationEntity> cancellationsPage = cancellationRepository.findAll(paging);
        List<CancellationResponse> cancellationsList = cancellationsPage.getContent().stream()
                .map(cancellation -> {
                    BookingResponse bookingResponse = bookingMapper.toResponse(cancellation.getBooking());
                    return cancellationMapper.toResponse(cancellation, bookingResponse);
                })
                .toList();
        return new PageResult<>(
                cancellationsList,
                cancellationsPage.getNumber(),
                cancellationsPage.getSize(),
                cancellationsPage.getTotalPages(),
                cancellationsPage.getTotalElements()
        );
    }
}
