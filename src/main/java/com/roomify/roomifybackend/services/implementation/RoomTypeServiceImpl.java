package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.FiltersRoomsType;
import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import com.roomify.roomifybackend.persistence.repository.RoomTypeRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomTypeResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.RoomTypeMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.interfaces.IRoomTypeService;
import com.roomify.roomifybackend.specification.SearchRoomTypeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements IRoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public SaveResponse saveRoomType(SaveRoomTypeRequest saveRoomTypeRequest) {
        roomTypeRepository.save(roomTypeMapper.toEntity(saveRoomTypeRequest));
        return new SaveResponse("Tipo de habitacion creada", LocalDate.now());
    }

    @Override
    public PageResult<RoomTypeResponse> getAllRoomType(Integer page, Integer size, FiltersRoomsType filtersRoomsType) {

        SearchRoomTypeSpecification spec = new SearchRoomTypeSpecification(filtersRoomsType.roomName() ,filtersRoomsType.roomCapacity(), filtersRoomsType.minPrice(), filtersRoomsType.maxPrice(), filtersRoomsType.amenityId());
        Pageable paging = PageRequest.of(page, size);
        Page<RoomTypeEntity> roomsTypes = roomTypeRepository.findAll(spec, paging);
        List<RoomTypeResponse> roomTypesList = roomTypeMapper.toListResponse(roomsTypes.getContent());
        return new PageResult<>(
                roomTypesList,
                roomsTypes.getNumber(),
                roomsTypes.getSize(),
                roomsTypes.getTotalPages(),
                roomsTypes.getTotalElements()
        );
    }

    @Override
    public RoomTypeResponse getRoomType(Long id) {
        RoomTypeEntity roomTypeFound = roomTypeRepository.findById(id).orElse(null);

        if(roomTypeFound == null) {
            throw new NoExistException("Tipo de habitacion no encontrada");
        }

        return roomTypeMapper.toResponse(roomTypeFound);
    }

    @Override
    public SaveResponse updateRoomType(Long id, SaveRoomTypeRequest saveRoomTypeRequest) {
        RoomTypeEntity roomTypeFound = roomTypeRepository.findById(id).orElse(null);

        if(roomTypeFound == null) {
            throw new NoExistException("Tipo de habitacion no encontrada");
        }

        roomTypeFound.setName(saveRoomTypeRequest.name());
        roomTypeFound.setDescription(saveRoomTypeRequest.description());

        roomTypeRepository.save(roomTypeFound);

        return new SaveResponse("Tipo de habitacion actualizada", LocalDate.now());
    }

    @Override
    public DeleteResponse deleteRoomType(Long id) {
        RoomTypeEntity roomTypeFound = roomTypeRepository.findById(id).orElse(null);

        if(roomTypeFound == null) {
            throw new NoExistException("Tipo de habitacion no encontrada");
        }
        roomTypeRepository.deleteById(id);
        return new DeleteResponse("Tipo de habitacion eliminada", LocalDate.now());
    }


}
