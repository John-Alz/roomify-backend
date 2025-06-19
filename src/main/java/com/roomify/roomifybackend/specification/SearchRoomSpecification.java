package com.roomify.roomifybackend.specification;

import com.roomify.roomifybackend.persistence.entity.*;
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

    private Long roomTypeId;
    private RoomStatus status;

    @Override
    public Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();


//        Join<RoomEntity, AmenityEntity> subAmenity = root.join("amenities");


        if (roomTypeId != null) {
            Join<RoomEntity, RoomTypeEntity> roomRoomTypeJoin = root.join("roomType");
            Predicate roomTypeNamePredicate = criteriaBuilder.equal(roomRoomTypeJoin.get("id"), roomTypeId);
            predicates.add(roomTypeNamePredicate);
        }

        if (status != null) {
            Predicate statusRoomPredicade = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusRoomPredicade);
        }



        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
