package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IRoomService {

    SaveResponse saveRoom(SaveRoomRequest saveRoomRequest);

    PageResult<RoomResponse> getAllRooms(Integer page, Integer size, boolean orderAsc);

    RoomResponse getRoomById(Long roomId);

    DeleteResponse deleteRoom(Long roomId);

    RoomResponse updateRoom(Long roomId, SaveRoomRequest saveRoomRequest);


}
