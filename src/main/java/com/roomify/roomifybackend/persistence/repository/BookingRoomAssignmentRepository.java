package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.BookingRoomAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRoomAssignmentRepository extends JpaRepository<BookingRoomAssignment, Long> {
}
