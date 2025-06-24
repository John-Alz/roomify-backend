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
public class SearchPaymentSpecification implements Specification<PaymentEntity> {

    private String numberBooking;
    private LocalDate dateFromPayment;
    private LocalDate dateToPayment;
    private BigDecimal priceMin;
    private Long roomTypeId;
    private PaymentStatus status;

    @Override
    public Predicate toPredicate(Root<PaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        Join<PaymentEntity, BookingEntity> paymentBookingJoin = root.join("booking");
        Join<BookingEntity, RoomTypeEntity> booKingRoomTypeJoin = paymentBookingJoin.join("roomType");

        if (numberBooking != null && !numberBooking.isEmpty()) {
            Expression<String> numberBookingFilter = criteriaBuilder.lower(paymentBookingJoin.get("reservationNumber"));
            Predicate searchNumberBooking = criteriaBuilder.like(numberBookingFilter, "%".concat(numberBooking).toLowerCase().concat("%"));
            predicates.add(searchNumberBooking);
        }

        if (dateFromPayment != null && dateToPayment != null) {
            Predicate dateCancellationPredicate = criteriaBuilder.between(root.get("created_at"), dateFromPayment, dateToPayment);
            predicates.add(dateCancellationPredicate);
        }

        if (priceMin != null) {
            Predicate priceMinPredicate = criteriaBuilder.greaterThanOrEqualTo(paymentBookingJoin.get("totalPrice"), priceMin);
            predicates.add(priceMinPredicate);
        }

        if (roomTypeId != null) {
            Predicate roomTypePredicate = criteriaBuilder.equal(booKingRoomTypeJoin.get("id"), roomTypeId);
            predicates.add(roomTypePredicate);
        }

        if (status != null) {
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), status);
            predicates.add(statusPredicate);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
