package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.BookingByDateEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.persistence.repository.BookingByDateRepository;
import com.roomify.roomifybackend.persistence.repository.RoomTypeRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveBookingRequest;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.utils.helpers.DateRangeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomAvailabilityService {

    private final BookingByDateRepository bookingByDateRepository;
    private final RoomTypeRepository roomTypeRepository;

    public boolean existsAvailability (SaveBookingRequest bookingRequest) {
        List<LocalDate> dates = DateRangeUtils.generateDates(bookingRequest.checkInDate(), bookingRequest.checkOutDate());

        for(LocalDate date : dates) {
            BookingByDateEntity bookingByDateEntity = bookingByDateRepository.findByRoomTypeAndDate(bookingRequest.roomTypeId(), date).orElse(null);
            int booked = bookingByDateEntity != null ? bookingByDateEntity.getBookingQuantity() : 0;

            RoomTypeEntity roomType = roomTypeRepository.findById(bookingRequest.roomTypeId()).orElse(null);
            assert roomType != null;
            int stock = roomType.getQuantity_available();

            if (booked + bookingRequest.numberOfRoom() > stock) {
                return false;
            }
        }

        return true;
    }

    public void updateReservationsByDate(SaveBookingRequest bookingRequest) {
        List<LocalDate> dates = DateRangeUtils.generateDates(bookingRequest.checkInDate(), bookingRequest.checkOutDate());
        RoomTypeEntity roomType = roomTypeRepository.findById(bookingRequest.roomTypeId()).orElse(null);
        if (roomType == null) {
            throw new NoExistException("Tipo de habitacion no encontrado");
        }
        for(LocalDate date : dates) {
            BookingByDateEntity bookingByDateEntity = bookingByDateRepository.findByRoomTypeAndDate(bookingRequest.roomTypeId(), date).orElse(null);

            if (bookingByDateEntity == null) {
                bookingByDateEntity = new BookingByDateEntity();
                bookingByDateEntity.setDate(date);
                bookingByDateEntity.setRoomType(roomType);
                bookingByDateEntity.setBookingQuantity(bookingRequest.numberOfRoom());
            } else {
                bookingByDateEntity.setBookingQuantity(bookingByDateEntity.getBookingQuantity() + bookingRequest.numberOfRoom());
            }
            bookingByDateRepository.save(bookingByDateEntity);
        }
    }

}
