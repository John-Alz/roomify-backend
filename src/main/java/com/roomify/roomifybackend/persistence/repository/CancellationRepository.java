package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.CancellationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CancellationRepository extends JpaRepository<CancellationEntity, Long>, JpaSpecificationExecutor<CancellationEntity> {

    Page<CancellationEntity> findAll(Pageable pageable);

    //Metrics
    @Query("SELECT COUNT(c) FROM CancellationEntity c WHERE MONTH(c.dateOfCancellation) = :month AND YEAR(c.dateOfCancellation) = :year")
    Long countByMonth(@Param("month") int month, @Param("year") int year);

}
