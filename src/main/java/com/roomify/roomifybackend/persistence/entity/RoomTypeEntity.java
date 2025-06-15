package com.roomify.roomifybackend.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_type")
public class RoomTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private int beds;
    private int bathRooms;
    private int meters;

    @ElementCollection
    @CollectionTable(name = "room_image", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "image_url")
    private List<String> images;
    private int quantity_available;
    private int capacity;
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = AmenityEntity.class)
    @JoinTable(name = "habitacion_amenidad", joinColumns = @JoinColumn(name = "habitacion_id"), inverseJoinColumns = @JoinColumn(name = "amenidad_id"))
    private Set<AmenityEntity> amenities;

//    private List<String> tags;

}
