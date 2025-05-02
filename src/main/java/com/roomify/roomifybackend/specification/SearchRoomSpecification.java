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

    private String roomType;
    private Integer roomCapacity;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Long amenityId;

    @Override
    public Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<RoomEntity, RoomTypeEntity> roomRoomTypeJoin = root.join("room_type");
        Join<RoomEntity, AmenityEntity> subAmenity = root.join("amenities");


        if (roomType != null && !roomType.isEmpty()) {
            Expression<String> roomTypeNameFilter = criteriaBuilder.lower(roomRoomTypeJoin.get("name"));
            Predicate roomTypeNamePredicate = criteriaBuilder.like(roomTypeNameFilter, "%".concat(roomType).trim().toLowerCase().concat("%"));
            predicates.add(roomTypeNamePredicate);
        }

        if(roomCapacity != null && !roomCapacity.toString().isEmpty()) {
            Predicate capacityPrecicate = criteriaBuilder.greaterThanOrEqualTo(root.get("room_capacity"), roomCapacity);
            predicates.add(capacityPrecicate);
        }

        // Filtro por disponibilidad
        if (checkIn != null && checkOut != null) {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<BookingEntity> booking = subquery.from(BookingEntity.class);
            Join<BookingEntity, RoomEntity> bookedRoom = booking.join("rooms");

            subquery.select(bookedRoom.get("id")).where(
                    criteriaBuilder.notEqual(booking.get("status"), BookingStatus.CANCELLED),
                    criteriaBuilder.lessThan(booking.get("checkInDate"), checkOut),
                    criteriaBuilder.greaterThan(booking.get("checkOutDate"), checkIn)
            );

            // Excluir habitaciones ocupadas
            predicates.add(criteriaBuilder.not(root.get("id").in(subquery)));
        }

        if (amenityId != null) {
            Predicate predicate = criteriaBuilder.equal(subAmenity.get("id"), amenityId);
            predicates.add(predicate);
        }


        if (minPrice != null && !minPrice.equals(BigDecimal.ZERO)) {
            Predicate priceGreaterThanEqualPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("room_price"), minPrice);
            predicates.add(priceGreaterThanEqualPredicate);
        }

        if (maxPrice != null && !maxPrice.equals(BigDecimal.ZERO)) {
            Predicate priceLessThanEqualPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("room_price"), maxPrice);
            predicates.add(priceLessThanEqualPredicate);
        }



        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
