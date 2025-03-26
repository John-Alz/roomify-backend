package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.repository.RoomTypeRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.RoomTypeMapper;
import com.roomify.roomifybackend.services.interfaces.IRoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RoomTypeImpl implements IRoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public SaveResponse saveRoomType(SaveRoomTypeRequest saveRoomTypeRequest) {
        roomTypeRepository.save(roomTypeMapper.toEntity(saveRoomTypeRequest));
        return new SaveResponse("Tipo de habitacion creada", LocalDate.now());
    }
}
