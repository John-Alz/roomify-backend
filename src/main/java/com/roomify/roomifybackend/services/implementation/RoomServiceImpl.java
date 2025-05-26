package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.persistence.repository.RoomRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.RoomMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.IRoomService;
import com.roomify.roomifybackend.specification.SearchRoomSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public SaveResponse saveRoom(SaveRoomRequest saveRoomRequest) {
        roomRepository.save(roomMapper.toEntity(saveRoomRequest));
        return new SaveResponse("Habitacion creada", LocalDate.now());
    }

    @Override
    public PageResult<RoomResponse> getAllRooms(
            Integer page,
            Integer size,
            boolean orderAsc,
            String roomType,
            Integer roomCapacity,
            LocalDate checkIn, LocalDate checkOut,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Long amenityId) {
//        Sort sort = orderAsc ? Sort.by("room_name").ascending() : Sort.by("room_name").descending();

        System.out.println("minPrice: " + minPrice);

//        SearchRoomSpecification spec = new SearchRoomSpecification(roomType, roomCapacity, checkIn, checkOut, minPrice, maxPrice, amenityId);

        Pageable paging = PageRequest.of(page, size);
        Page<RoomEntity> roomPage = roomRepository.findAll(paging);
        List<RoomResponse> roomsList = roomMapper.toResponseList(roomPage.getContent());
        return new PageResult<>(
                roomsList,
                roomPage.getNumber(),
                roomPage.getSize(),
                roomPage.getTotalPages(),
                roomPage.getTotalElements()
        );
    }

    @Override
    public RoomResponse getRoomById(Long roomId) {
        RoomEntity room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            throw new NoExistException("Habitacion no encontrada");
        }
        return roomMapper.toResponse(room);
    }

    @Override
    public DeleteResponse deleteRoom(Long roomId) {
        RoomEntity room = roomRepository.findById(roomId).orElse(null);
        if (room == null) {
            throw new NoExistException("Habitacion no encontrada");
        }
        roomRepository.deleteById(roomId);
        return new DeleteResponse("Habitacion eliminada", LocalDate.now());
    }

    @Override
    public RoomResponse updateRoom(Long roomId, SaveRoomRequest saveRoomRequest) {

        RoomEntity roomFound = roomRepository.findById(roomId).orElse(null);

        if (roomFound == null) {
            throw new NoExistException("Habitacion no encontrada");
        }

        roomFound.setRoom_rumber(saveRoomRequest.room_number());
        roomFound.setRoom_description(saveRoomRequest.room_description());
        roomFound.setRoom_images(saveRoomRequest.room_images());
        roomFound.setRoom_availability(saveRoomRequest.room_availability());
        roomFound.setRoom_capacity(saveRoomRequest.room_capacity());
        roomFound.setRoom_price(saveRoomRequest.room_price());
//        roomFound.setRoom_type(new RoomTypeEntity(saveRoomRequest.room_type_id(), null, null));
        Set<AmenityEntity> updatedAmenities = saveRoomRequest.amenities_id().stream()
                        .map(id -> new AmenityEntity(id, null, null, null))
                        .collect(Collectors.toSet());
        roomFound.setAmenities(updatedAmenities);
        roomRepository.save(roomFound);

        return roomMapper.toResponse(roomFound);
    }
}
