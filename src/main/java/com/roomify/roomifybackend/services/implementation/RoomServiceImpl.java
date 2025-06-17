package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.RoomRepository;
import com.roomify.roomifybackend.persistence.repository.RoomTypeRepository;
import com.roomify.roomifybackend.persistence.repository.UserRepository;
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
    private final RoomTypeRepository roomTypeRepository;
    private final UserRepository userRepository;
    private final RoomMapper roomMapper;

    @Override
    public SaveResponse saveRoom(SaveRoomRequest saveRoomRequest) {
        RoomTypeEntity roomTypeFound = roomTypeRepository.findById(saveRoomRequest.room_type_id()).orElse(null);
//        UserEntity userFound = userRepository.findById(saveRoomRequest.currentClient()).orElse(null);
        RoomEntity roomEntity = roomMapper.toEntity(saveRoomRequest, roomTypeFound);
        roomRepository.save(roomEntity);
        return new SaveResponse("Habitacion creada", LocalDate.now());
    }

    @Override
    public PageResult<RoomResponse> getAllRooms(
            Integer page,
            Integer size,
            boolean orderAsc,
            Long roomTypeId) {
//        Sort sort = orderAsc ? Sort.by("room_name").ascending() : Sort.by("room_name").descending();

        SearchRoomSpecification spec = new SearchRoomSpecification(roomTypeId);
        Pageable paging = PageRequest.of(page, size);
        Page<RoomEntity> roomPage = roomRepository.findAll(spec, paging);
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
        RoomTypeEntity roomTypeFound = roomTypeRepository.findById(saveRoomRequest.room_type_id()).orElse(null);

        if (roomFound == null) {
            throw new NoExistException("Habitacion no encontrada");
        }

        roomFound.setRoom_number(saveRoomRequest.room_number());
        roomFound.setRoomType(roomTypeFound);
        roomFound.setStatus(saveRoomRequest.status());
        roomFound.setLastMaintenance(saveRoomRequest.lastMaintenance());
        roomFound.setNotes(saveRoomRequest.notes());

        roomRepository.save(roomFound);
        return roomMapper.toResponse(roomFound);
    }
}
