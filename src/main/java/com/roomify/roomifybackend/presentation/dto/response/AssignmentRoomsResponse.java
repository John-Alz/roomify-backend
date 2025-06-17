package com.roomify.roomifybackend.presentation.dto.response;

import java.util.List;

public record AssignmentRoomsResponse(Long bookingId, List<RoomResponse> rooms) {
}
