package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<AmenityEntity, Long> {
}
