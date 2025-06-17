package com.roomify.roomifybackend.services.interfaces;

import com.roomify.roomifybackend.presentation.dto.request.AssignmentRoomsRequest;
import com.roomify.roomifybackend.presentation.dto.response.AssignmentRoomsResponse;
import com.roomify.roomifybackend.presentation.dto.response.RoomResponse;
import com.roomify.roomifybackend.presentation.dto.response.SaveResponse;

public interface IBookingRoomAssignmentService {

    SaveResponse saveBookingRoomAssignment(AssignmentRoomsRequest assignRoomsRequest);

    AssignmentRoomsResponse getBookingRoomAssignmentByBookingId(Long bookingId);

}
