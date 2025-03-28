package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.repository.RoomRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.RoomMapper;
import com.roomify.roomifybackend.services.interfaces.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public PageResult<RoomResponse> getAllRooms(Integer page, Integer size) {
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
        return roomMapper.toResponse(room);
    }

    @Override
    public DeleteResponse deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
        return new DeleteResponse("Habitacion eliminada", LocalDate.now());
    }
}
