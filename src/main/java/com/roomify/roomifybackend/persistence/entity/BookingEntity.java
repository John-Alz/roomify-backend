package com.roomify.roomifybackend.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

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

    private LocalDateTime bookingDate = LocalDateTime.now();
    private BookingStatus status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private float totalPrice;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoomEntity.class)
    @JoinTable(name = "booking_room", joinColumns =  @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<RoomEntity> rooms;





}
