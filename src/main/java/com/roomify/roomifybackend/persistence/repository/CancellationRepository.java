package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.CancellationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CancellationRepository extends JpaRepository<CancellationEntity, Long>, JpaSpecificationExecutor<CancellationEntity> {

    Page<CancellationEntity> findAll(Pageable pageable);

}
