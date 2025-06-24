package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Long>, JpaSpecificationExecutor<BookingEntity> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BookingEntity b WHERE b.roomType.id = :roomTypeId AND b.status IN (:statuses)")
    boolean existsActiveBookingsByRoomTypeId(@Param("roomTypeId") Long roomTypeId, @Param("statuses") List<BookingStatus> statuses);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BookingEntity b WHERE b.clientId.id = :userId ")
    boolean existsActiveBookingsByUserId(@Param("userId") Long id);

    // Metrics
    @Query("SELECT COUNT(b) FROM BookingEntity b WHERE MONTH(b.bookingDate) = :month AND YEAR(b.bookingDate) = :year")
    Long countByMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT SUM(b.totalPrice) FROM BookingEntity b WHERE b.status = :status AND MONTH(b.bookingDate) = :month AND YEAR(b.bookingDate) = :year")
    BigDecimal sumPaidTotalPriceByMonth(@Param("month") int month, @Param("year") int year, @Param("status") BookingStatus status);

}
