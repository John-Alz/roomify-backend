package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Long>, JpaSpecificationExecutor<RoomTypeEntity> {

    Page<RoomTypeEntity> findAll(Pageable pageable);

}
