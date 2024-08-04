package com.allstock.app.repositories;

import com.allstock.app.persistence.entities.CardEntity;
import com.allstock.app.persistence.entities.EmpresaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<CardEntity, Long> {

}
