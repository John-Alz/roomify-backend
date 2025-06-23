package com.roomify.roomifybackend.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cancellations")
public class CancellationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private BookingEntity booking;

    private LocalDate dateOfCancellation = LocalDate.now();

    private String reasonForCancellation;

    @ManyToOne
    @JoinColumn(name = "cancelled_by_user_id")
    private UserEntity cancelledBy;

}
