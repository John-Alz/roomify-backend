package com.roomify.roomifybackend.services.implementation;

import com.roomify.roomifybackend.persistence.entity.*;
import com.roomify.roomifybackend.persistence.repository.BookingByDateRepository;
import com.roomify.roomifybackend.persistence.repository.BookingRepository;
import com.roomify.roomifybackend.persistence.repository.RoomTypeRepository;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomTypeResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;
import com.roomify.roomifybackend.presentation.mappers.RoomTypeMapper;
import com.roomify.roomifybackend.services.exception.NoExistException;
import com.roomify.roomifybackend.services.exception.RoomWithBookingActiveException;
import com.roomify.roomifybackend.services.interfaces.IRoomTypeService;
import com.roomify.roomifybackend.specification.SearchRoomTypeSpecification;
import com.roomify.roomifybackend.utils.helpers.DateRangeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements IRoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final BookingByDateRepository bookingByDateRepository;
    private final BookingRepository bookingRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public SaveResponse saveRoomType(SaveRoomTypeRequest saveRoomTypeRequest) {
        System.out.println(saveRoomTypeRequest.price());
        roomTypeRepository.save(roomTypeMapper.toEntity(saveRoomTypeRequest));
        return new SaveResponse("Tipo de habitacion creada", LocalDate.now());
    }

    @Override
    public PageResult<RoomTypeResponse> getAllRoomType(Integer page, Integer size, FiltersRoomsType filtersRoomsType) {
        List<LocalDate> dates = DateRangeUtils.generateDates(filtersRoomsType.checkIn(), filtersRoomsType.checkOut());
        SearchRoomTypeSpecification spec = new SearchRoomTypeSpecification(filtersRoomsType.roomName() ,filtersRoomsType.roomCapacity(), filtersRoomsType.minPrice(), filtersRoomsType.maxPrice(), filtersRoomsType.amenityId());
        Pageable paging = PageRequest.of(page, size);
        Page<RoomTypeEntity> roomsTypes = roomTypeRepository.findAll(spec, paging);
        List<RoomTypeResponse> roomTypesList = roomTypeMapper.toListResponse(roomsTypes.getContent());
        List<RoomTypeResponse> roomTypeListAvailable = roomTypesList.stream()
                .filter(roomsType -> {
                    int stock = roomsType.quantity_available();
                    for (LocalDate date : dates) {
                        BookingByDateEntity bookingByDateEntity = bookingByDateRepository.findByRoomTypeAndDate(roomsType.id(), date).orElse(null);
                        int booked = bookingByDateEntity != null ? bookingByDateEntity.getBookingQuantity() : 0;
                        if (booked >= stock) return false;
                    }
                    return true;
                })
                .toList();
        return new PageResult<>(
                roomTypeListAvailable,
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
        roomTypeFound.setBeds(saveRoomTypeRequest.beds());
        roomTypeFound.setBathRooms(saveRoomTypeRequest.bathRooms());
        roomTypeFound.setMeters(saveRoomTypeRequest.meters());
        roomTypeFound.setImages(saveRoomTypeRequest.images());
        roomTypeFound.setQuantity_available(saveRoomTypeRequest.quantity_available());
        roomTypeFound.setCapacity(saveRoomTypeRequest.capacity());
        roomTypeFound.setPrice(saveRoomTypeRequest.price());
        Set<AmenityEntity>  updatedAmenities = saveRoomTypeRequest.amenities_id().stream()
                        .map(amenityId -> new AmenityEntity(amenityId, null, null, null))
                        .collect(Collectors.toSet());
        roomTypeFound.setAmenities(updatedAmenities);

        roomTypeRepository.save(roomTypeFound);

        return new SaveResponse("Tipo de habitacion actualizada", LocalDate.now());
    }

    @Override
    public DeleteResponse deleteRoomType(Long id) {
        RoomTypeEntity roomTypeFound = roomTypeRepository.findById(id).orElse(null);

        if(roomTypeFound == null) {
            throw new NoExistException("Tipo de habitacion no encontrada");
        }

        boolean bookingFound = bookingRepository.existsActiveBookingsByRoomTypeId(
                id,
                List.of(BookingStatus.CONFIRMADA, BookingStatus.CHECK_IN)
        );

        if (bookingFound) {
            System.out.println("El tipo de habitación tiene reservas activas");
            throw new RoomWithBookingActiveException();
        }
        roomTypeRepository.deleteById(id);
        return new DeleteResponse("Tipo de habitacion eliminada", LocalDate.now());
    }


}
