package com.allstock.app.repositories;

import com.allstock.app.persistence.entities.RolesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository  extends CrudRepository<RolesEntity, Long> {

    List<RolesEntity> findRolesEntitiesByRolesEnumIn(List<String> roleNames);
}
