package com.roomify.roomifybackend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private UserEntity clientId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
    @JoinColumn(name = "room_type_id")
    private RoomTypeEntity roomType;

    private LocalDateTime bookingDate = LocalDateTime.now();
    private BookingStatus status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalPrice;
    private Integer numberOfRoom;

}
