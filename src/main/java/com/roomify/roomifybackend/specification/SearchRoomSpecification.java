package com.roomify.roomifybackend.specification;

import com.roomify.roomifybackend.persistence.entity.RoomEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SearchRoomSpecification implements Specification<RoomEntity> {

    private String roomType;
    private Integer roomCapacity;
//    private LocalDate checkIn;
//    private LocalDate checkOut;
//    private String amanity;
//    private BigDecimal minPrice;
//    private BigDecimal maxPrice;

    @Override
    public Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<RoomEntity, RoomTypeEntity> roomRoomTypeJoin = root.join("room_type");

        if (roomType != null && !roomType.isEmpty()) {
            Expression<String> roomTypeNameFilter = criteriaBuilder.lower(roomRoomTypeJoin.get("name"));
            Predicate roomTypeNamePredicate = criteriaBuilder.like(roomTypeNameFilter, "%".concat(roomType).trim().toLowerCase().concat("%"));
            predicates.add(roomTypeNamePredicate);
        }

        if(roomCapacity != null && !roomCapacity.toString().isEmpty()) {
            Predicate capacityPrecicate = criteriaBuilder.greaterThanOrEqualTo(root.get("room_capacity"), roomCapacity);
            predicates.add(capacityPrecicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
