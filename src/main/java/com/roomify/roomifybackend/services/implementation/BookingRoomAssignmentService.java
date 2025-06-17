package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.BookingRoomAssignmentRepository;
import com.roomify.roomifybackend.persistence.repository.RoomRepository;
import com.roomify.roomifybackend.presentation.dto.request.AssignmentRoomsRequest;
import com.roomify.roomifybackend.presentation.dto.response.AssignmentRoomsResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.RoomMapper;
import com.roomify.roomifybackend.services.exception.InvalidBookingStatus;
import com.roomify.roomifybackend.services.exception.InvalidSizeRoomsIds;
import com.roomify.roomifybackend.services.interfaces.IBookingRoomAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingRoomAssignmentService implements IBookingRoomAssignmentService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final BookingRoomAssignmentRepository bookingRoomAssignmentRepository;
    private final RoomMapper roomMapper;

    @Override
    public SaveResponse saveBookingRoomAssignment(AssignmentRoomsRequest assignRoomsRequest) {
        BookingEntity booking = bookingRepository.findById(assignRoomsRequest.bookingId()).orElse(null);
        assert booking != null;
        if (!booking.getStatus().equals(BookingStatus.CONFIRMADA)) {
            throw new InvalidBookingStatus();
        }

        if (assignRoomsRequest.roomsIds().size() != booking.getNumberOfRoom()) {
            throw new InvalidSizeRoomsIds();
        }

        List<RoomEntity> rooms = roomRepository.findAllById(assignRoomsRequest.roomsIds());

        //Aca va una validacion pata el status de la habiatcion

        for(RoomEntity room : rooms) {
            BookingRoomAssignment assignment = BookingRoomAssignment.builder()
                    .booking(booking)
                    .room(room)
                    .assignedAt(LocalDateTime.now())
                    .status(AssignmentStatus.ASSIGNED)
                    .build();
            bookingRoomAssignmentRepository.save(assignment);
            room.setStatus(RoomStatus.OCUPADA);
            roomRepository.save(room);
        }

        booking.setStatus(BookingStatus.CHECK_IN);
        bookingRepository.save(booking);
        return new SaveResponse("Se asignaron las habitaciones a la reserva", LocalDate.now());
    }

    @Override
    public AssignmentRoomsResponse getBookingRoomAssignmentByBookingId(Long bookingId) {
        BookingEntity bookingFound = bookingRepository.findById(bookingId).orElse(null);
        List<RoomEntity> assignedRooms = bookingFound.getRoomAssignments().stream()
                .map(BookingRoomAssignment::getRoom)
                .toList();
        List<RoomResponse> roomResponses = roomMapper.toResponseList(assignedRooms);

        return new AssignmentRoomsResponse(bookingId, roomResponses);
    }
}
