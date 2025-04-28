package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>, JpaSpecificationExecutor<RoomEntity> {

    Page<RoomEntity> findAll(Pageable pageable);

    Optional<RoomEntity> findById(Long id);

}
