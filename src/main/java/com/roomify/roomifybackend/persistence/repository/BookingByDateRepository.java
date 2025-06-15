package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.BookingByDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingByDateRepository extends JpaRepository<BookingByDateEntity, Long> {

    @Query("SELECT b FROM BookingByDateEntity b WHERE b.roomType.id = ?1 AND b.date = ?2")
    Optional<BookingByDateEntity> findByRoomTypeAndDate(Long roomTypeId, LocalDate date);

}
