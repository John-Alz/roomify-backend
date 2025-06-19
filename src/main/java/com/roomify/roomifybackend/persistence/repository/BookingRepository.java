package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BookingEntity b WHERE b.roomType.id = :roomTypeId AND b.status IN (:statuses)")
    boolean existsActiveBookingsByRoomTypeId(@Param("roomTypeId") Long roomTypeId, @Param("statuses") List<BookingStatus> statuses);

}
