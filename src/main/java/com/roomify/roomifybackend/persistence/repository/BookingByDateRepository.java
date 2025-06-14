package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.BookingByDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingByDateRepository extends JpaRepository<BookingByDateEntity, Long> {
}
