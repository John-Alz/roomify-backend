package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
