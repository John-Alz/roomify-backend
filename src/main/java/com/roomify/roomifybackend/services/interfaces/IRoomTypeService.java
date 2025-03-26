package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.request.SaveRoomTypeRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IRoomTypeService {

    SaveResponse saveRoomType(SaveRoomTypeRequest saveRoomTypeRequest);
}
