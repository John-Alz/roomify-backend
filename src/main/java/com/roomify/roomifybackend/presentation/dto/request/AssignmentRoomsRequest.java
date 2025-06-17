package com.roomify.roomifybackend.presentation.dto.request;

import java.util.List;

public record AssignmentRoomsRequest(Long bookingId, List<Long> roomsIds) {
}
