package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
