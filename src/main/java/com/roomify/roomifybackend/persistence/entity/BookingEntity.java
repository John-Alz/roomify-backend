package com.roomify.roomifybackend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    private String reservationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private UserEntity clientId;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RoomTypeEntity.class)
    @JoinColumn(name = "room_type_id")
    private RoomTypeEntity roomType;

    private LocalDateTime bookingDate = LocalDateTime.now();
    private BookingStatus status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    @Column(precision = 10, scale = 3)
    private BigDecimal totalPrice;
    private Integer numberOfRoom;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingRoomAssignment> roomAssignments;

}
