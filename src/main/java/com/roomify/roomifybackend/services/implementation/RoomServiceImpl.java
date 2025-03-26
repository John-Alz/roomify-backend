package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.repository.RoomRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.RoomMapper;
import com.roomify.roomifybackend.services.interfaces.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
