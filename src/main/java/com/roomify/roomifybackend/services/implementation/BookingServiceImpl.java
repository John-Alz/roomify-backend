package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.RoomRepository;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;
import com.roomify.roomifybackend.presentation.dto.response.BookingResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.BookingMapper;
import com.roomify.roomifybackend.services.exception.CheckInInvalidException;
import com.roomify.roomifybackend.services.exception.CheckOutInvalidException;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final BookingMapper bookingMapper;

    @Override
    public SaveResponse saveBooking(SaveBookingRequest saveBookingRequest) {

        UserEntity userFound = userRepository.findById(saveBookingRequest.clientId()).orElse(null);
        Set<RoomEntity> roomsFound = saveBookingRequest.rooms().stream()
                .map(id -> roomRepository.findById(id).orElseThrow(() -> new NoExistException("Habitacion no encontrada")))
                .collect(Collectors.toSet());

        if (userFound == null) {
            throw new NoExistException("Usuario no encontrado");
        }

        if (saveBookingRequest.checkInDate().equals(LocalDate.now()) || saveBookingRequest.checkInDate().isBefore(LocalDate.now())) {
            throw new CheckInInvalidException();
        }

        if (saveBookingRequest.checkOutDate().equals(saveBookingRequest.checkInDate()) || saveBookingRequest.checkOutDate().isBefore(saveBookingRequest.checkInDate())) {
            throw new CheckOutInvalidException();
        }

        BookingEntity booking = bookingMapper.toBookingEntity(saveBookingRequest, userFound, roomsFound);

        float totalBookingPrice = 0;
        for(RoomEntity room : roomsFound) {
            totalBookingPrice += room.getRoom_price();
        }

        booking.setTotalPrice(totalBookingPrice);

        bookingRepository.save(booking);
        return new SaveResponse("Reserva creada", LocalDate.now());
    }

    @Override
    public PageResult<BookingResponse> getAllBookings(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<BookingEntity> bookingPage = bookingRepository.findAll(paging);
        List<BookingResponse> bookingResponseList = bookingMapper.roomResponseList(bookingPage.getContent());
        return new PageResult<>(
                bookingResponseList,
                bookingPage.getNumber(),
                bookingPage.getSize(),
                bookingPage.getTotalPages(),
                bookingPage.getTotalElements()
        );
    }

    @Override
    public BookingResponse getBooking(Long bookingId) {

        BookingEntity bookingFound = bookingRepository.findById(bookingId).orElse(null);

        if (bookingFound == null) {
            throw new NoExistException("Esa reserva no existe.");
        }

        return bookingMapper.toResponse(bookingFound);
    }

    @Override
    public BookingResponse updateBooking(Long bookingId, SaveBookingRequest saveBookingRequest) {
        BookingEntity bookingFound = bookingRepository.findById(bookingId).orElse(null);

        if (bookingFound == null) {
            throw new NoExistException("Esa reserva no existe.");
        }
        Set<RoomEntity> roomsFound = saveBookingRequest.rooms().stream()
                .map(id -> roomRepository.findById(id).orElseThrow(() -> new NoExistException("Habitacion no encontrada")))
                .collect(Collectors.toSet());

        bookingFound.setStatus(saveBookingRequest.status());
        bookingFound.setCheckInDate(saveBookingRequest.checkInDate());
        bookingFound.setCheckOutDate(saveBookingRequest.checkOutDate());
        bookingFound.setRooms(roomsFound);

        bookingRepository.save(bookingFound);

        return bookingMapper.toResponse(bookingFound);
    }
}
