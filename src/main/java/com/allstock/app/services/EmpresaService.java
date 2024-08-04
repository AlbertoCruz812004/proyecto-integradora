package com.allstock.app.services;

import java.util.Optional;

import com.allstock.app.persistence.entities.EmpresaEntity;

public interface EmpresaService {

    EmpresaEntity save(EmpresaEntity t);

    void update(EmpresaEntity t);

    boolean delete(EmpresaEntity t);

    Optional<EmpresaEntity> findById(Long id);

    Iterable<EmpresaEntity> findAllById();
}
