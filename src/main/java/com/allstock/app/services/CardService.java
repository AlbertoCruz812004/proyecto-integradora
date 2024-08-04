package com.allstock.app.services;

import com.allstock.app.persistence.entities.CardEntity;

import java.util.Optional;

public interface CardService {

    CardEntity save(CardEntity t);

    void update(CardEntity t);

    boolean delete(CardEntity t);

    Optional<CardEntity> findById(Long id);

    Iterable<CardEntity> findAllById();
}
