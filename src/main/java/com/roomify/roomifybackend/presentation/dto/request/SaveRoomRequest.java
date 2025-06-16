package com.roomify.roomifybackend.presentation.dto.request;

import com.roomify.roomifybackend.persistence.entity.RoomStatus;
import com.roomify.roomifybackend.persistence.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record SaveRoomRequest(
        String room_number,
        Long room_type_id,
        RoomStatus status,
        LocalDate lastMaintenance,
        String notes) {
}
