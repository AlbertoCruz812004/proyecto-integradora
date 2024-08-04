package com.allstock.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allstock.app.persistence.entities.EmpresaEntity;

@Repository
public interface EmpresaRepository extends CrudRepository<EmpresaEntity, Long> {

}
