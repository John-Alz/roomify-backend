package com.roomify.roomifybackend.presentation.dto.request;

import java.util.List;

public record AssignRoomsRequest(Long bookingId, List<Long> roomsIds) {
}
