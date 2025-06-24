package com.roomify.roomifybackend.specification;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.BookingStatus;
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
public class SearchBookingSpecificaction implements Specification<BookingEntity> {

    private String numberBooking;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal priceMin;
    private Long roomTypeId;
    private BookingStatus status;

    @Override
    public Predicate toPredicate(Root<BookingEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<BookingEntity, RoomTypeEntity> bookingRoomTypeJoin = root.join("roomType");

        if (numberBooking != null && !numberBooking.isEmpty()) {
            Predicate searchNumberBooking = criteriaBuilder.like(root.get("reservationNumber"), "%".concat(numberBooking).toLowerCase().concat("%"));
            predicates.add(searchNumberBooking);
        }

        if (checkInDate != null && checkOutDate != null) {
            Predicate dateChekInPredicate = criteriaBuilder.between(root.get("checkInDate"), checkInDate, checkOutDate);
            predicates.add(dateChekInPredicate);
        }

        if (priceMin != null) {
            Predicate priceMinPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("totalPrice"), priceMin);
            predicates.add(priceMinPredicate);
        }

        if (roomTypeId != null) {
            Predicate roomTypePredicate = criteriaBuilder.equal(bookingRoomTypeJoin.get("id"), roomTypeId);
            predicates.add(roomTypePredicate);
        }

        if (status != null) {
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
