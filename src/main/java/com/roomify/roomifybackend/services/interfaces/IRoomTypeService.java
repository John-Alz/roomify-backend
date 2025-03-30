package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.persistence.entity.PageResult;
import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.DeleteResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomTypeResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IRoomTypeService {

    SaveResponse saveRoomType(SaveRoomTypeRequest saveRoomTypeRequest);

    PageResult<RoomTypeResponse> getAllRoomType(Integer page, Integer size);

    RoomTypeResponse getRoomType(Long id);

    SaveResponse updateRoomType(Long id,  SaveRoomTypeRequest saveRoomTypeRequest);

    DeleteResponse deleteRoomType(Long id);

}
