package com.roomify.roomifybackend.specification;

import com.roomify.roomifybackend.persistence.entity.AmenityEntity;
import com.roomify.roomifybackend.persistence.entity.RoomTypeEntity;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Expr;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SearchRoomTypeSpecification implements Specification<RoomTypeEntity> {

    private String roomName;
    private Integer roomCapacity;
//    private LocalDate checkIn;
//    private LocalDate checkOut;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Long amenityId;

    @Override
    public Predicate toPredicate(Root<RoomTypeEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<RoomTypeEntity, AmenityEntity> amenityEntityJoin = root.join("amenities");

        if (roomName != null && !roomName.isEmpty()) {
            Expression roomNameExpression = criteriaBuilder.lower(root.get("name"));
            Predicate predicatename= criteriaBuilder.like(roomNameExpression, "%".concat(roomName).trim().toLowerCase().concat("%"));
            predicates.add(predicatename);
        }

        if (roomCapacity != null && !roomCapacity.toString().isEmpty()) {
            Predicate predicateCapacity = criteriaBuilder.greaterThanOrEqualTo(root.get("capacity"), roomCapacity);
            predicates.add(predicateCapacity);
        }

        if (amenityId != null) {
            Predicate predicate = criteriaBuilder.equal(amenityEntityJoin.get("id"), amenityId);
            predicates.add(predicate);
        }


        if (minPrice != null && !minPrice.equals(BigDecimal.ZERO)) {
            Predicate priceGreaterThanEqualPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            predicates.add(priceGreaterThanEqualPredicate);
        }

        if (maxPrice != null && !maxPrice.equals(BigDecimal.ZERO)) {
            Predicate priceLessThanEqualPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            predicates.add(priceLessThanEqualPredicate);
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
