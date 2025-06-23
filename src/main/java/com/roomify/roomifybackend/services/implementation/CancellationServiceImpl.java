package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.CancellationRepository;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveCancellationRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.CancellationResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.dto.response.UserResponse;
import com.roomify.roomifybackend.presentation.mappers.BookingMapper;
import com.roomify.roomifybackend.presentation.mappers.CancellationMapper;
import com.roomify.roomifybackend.presentation.mappers.UserMapper;
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
    private final UserRepository userRepository;
    private final CancellationMapper cancellationMapper;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;

    @Override
    public SaveResponse saveCancellation(SaveCancellationRequest cancellationRequest) {
        BookingEntity bookingFound = bookingRepository.findById(cancellationRequest.bookingId()).orElse(null);
        UserEntity userFound = userRepository.findById(cancellationRequest.userId()).orElse(null);
        if (bookingFound == null) {
            throw new NoExistException("Esa reserva no existe.");
        }
        if (userFound == null) {
            throw new NoExistException("Ese usuario no existe.");
        }
        CancellationEntity cancellation = cancellationMapper.toCancellationEntity(cancellationRequest, bookingFound, userFound);
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
                    UserResponse userResponse = userMapper.toResponse(cancellation.getCancelledBy());
                    return cancellationMapper.toResponse(cancellation, bookingResponse, userResponse);
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

    @Override
    public CancellationResponse getCancellation(Long cancellationId) {
        CancellationEntity cancellationFound = cancellationRepository.findById(cancellationId).orElse(null);
        BookingEntity bookingFound = bookingRepository.findById(cancellationFound.getBooking().getId()).orElse(null);
        UserEntity userFound = userRepository.findById(cancellationFound.getCancelledBy().getId()).orElse(null);
        if(cancellationFound == null) {
            throw new NoExistException("La cancelacion no existe.");
        }
        if(bookingFound == null) {
            throw new NoExistException("La reserva no existe.");
        }
        if(userFound == null) {
            throw new NoExistException("El usuario no existe.");
        }
        BookingResponse bookingResponse = bookingMapper.toResponse(bookingFound);
        UserResponse userResponse = userMapper.toResponse(userFound);
        return cancellationMapper.toResponse(cancellationFound, bookingResponse, userResponse);
    }


}
