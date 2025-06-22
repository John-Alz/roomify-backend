package com.roomify.roomifybackend.persistence.repository;

import com.roomify.roomifybackend.persistence.entity.RoleEntity;
import com.roomify.roomifybackend.persistence.entity.RoleEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);

    @Query("SELECT r FROM RoleEntity r WHERE r.roleEnum = ?1 ")
    Optional<RoleEntity> findRoleEnum(RoleEnum role);

}
