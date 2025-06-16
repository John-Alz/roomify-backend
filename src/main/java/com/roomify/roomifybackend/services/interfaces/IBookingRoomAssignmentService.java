package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.request.AssignRoomsRequest;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IBookingRoomAssignmentService {

    SaveResponse saveBookingRoomAssignment(AssignRoomsRequest assignRoomsRequest);

}
