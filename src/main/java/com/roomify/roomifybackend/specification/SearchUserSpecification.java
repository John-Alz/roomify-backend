package com.roomify.roomifybackend.specification;

import com.roomify.roomifybackend.persistence.entity.RoleEntity;
import com.roomify.roomifybackend.persistence.entity.UserEntity;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class SearchUserSpecification implements Specification<UserEntity> {

    private String email;
    private String role;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();

        if (email != null && !email.isEmpty()) {
            Predicate emailSearchPredicate = criteriaBuilder.like(root.get("email"), "%".concat(email).toLowerCase().concat("%"));
            predicates.add(emailSearchPredicate);
        }

        if (role != null && !role.isEmpty()) {
            Join<UserEntity, RoleEntity> joinUserRoleEntity = root.join("role");
            Predicate roleSearchPredicate = criteriaBuilder.equal(joinUserRoleEntity.get("roleEnum"), role);
            predicates.add(roleSearchPredicate);
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
