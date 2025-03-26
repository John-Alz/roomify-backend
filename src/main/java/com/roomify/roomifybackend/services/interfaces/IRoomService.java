package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.request.SaveRoomRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IRoomService {

    SaveResponse saveRoom(SaveRoomRequest saveRoomRequest);


}
