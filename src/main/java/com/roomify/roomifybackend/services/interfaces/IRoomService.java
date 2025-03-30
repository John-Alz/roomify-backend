package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IRoomService {

    SaveResponse saveRoom(SaveRoomRequest saveRoomRequest);

    PageResult<RoomResponse> getAllRooms(Integer page, Integer size);

    RoomResponse getRoomById(Long roomId);

    DeleteResponse deleteRoom(Long roomId);

    RoomResponse updateRoom(Long roomId, SaveRoomRequest saveRoomRequest);


}
