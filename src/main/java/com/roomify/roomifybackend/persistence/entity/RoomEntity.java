package com.roomify.roomifybackend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int room_rumber;
    private String room_description;

    @ElementCollection
    @CollectionTable(name = "room_image", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "image_url")
    private List<String> room_images;
    private String room_availability;
    private int room_capacity;
    private float room_price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id")
    private RoomTypeEntity room_type;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = AmenityEntity.class)
    @JoinTable(name = "habitacion_amenidad", joinColumns = @JoinColumn(name = "habitacion_id"), inverseJoinColumns = @JoinColumn(name = "amenidad_id"))
    private Set<AmenityEntity> amenities;
}
