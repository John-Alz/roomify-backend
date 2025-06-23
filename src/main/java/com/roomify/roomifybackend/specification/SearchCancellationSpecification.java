package com.roomify.roomifybackend.specification;

import com.roomify.roomifybackend.persistence.entity.BookingEntity;
import com.roomify.roomifybackend.persistence.entity.CancellationEntity;
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
public class SearchCancellationSpecification implements Specification<CancellationEntity> {

    private String numberBooking;
    private LocalDate dateFromCancellation;
    private LocalDate dateToCancellation;
    private BigDecimal priceMin;
    private Long roomTypeId;


    @Override
    public Predicate toPredicate(Root<CancellationEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<CancellationEntity, BookingEntity> cancellationBookingJoin = root.join("booking");
        Join<BookingEntity, RoomTypeEntity> booKingRoomTypeJoin = cancellationBookingJoin.join("roomType");

        if (numberBooking != null && !numberBooking.isEmpty()) {
            Expression<String> numberBookingFilter = criteriaBuilder.lower(cancellationBookingJoin.get("reservationNumber"));
            Predicate searchNumberBooking = criteriaBuilder.like(numberBookingFilter, "%".concat(numberBooking).toLowerCase().concat("%"));
            predicates.add(searchNumberBooking);
        }

        if (dateFromCancellation != null && dateToCancellation != null) {
            Predicate dateCancellationPredicate = criteriaBuilder.between(root.get("dateOfCancellation"), dateFromCancellation, dateToCancellation);
            predicates.add(dateCancellationPredicate);
        }

        if (priceMin != null) {
            Predicate priceMinPredicate = criteriaBuilder.greaterThanOrEqualTo(cancellationBookingJoin.get("totalPrice"), priceMin);
            predicates.add(priceMinPredicate);
        }

        if (roomTypeId != null) {
            Predicate roomTypePredicate = criteriaBuilder.equal(booKingRoomTypeJoin.get("id"), roomTypeId);
            predicates.add(roomTypePredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
