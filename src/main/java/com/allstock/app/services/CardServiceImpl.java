package com.allstock.app.services;

import com.allstock.app.persistence.entities.CardEntity;
import com.allstock.app.persistence.entities.EmpresaEntity;
import com.allstock.app.repositories.CardRepository;
import com.allstock.app.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository repository;

    @Autowired
    public CardServiceImpl(CardRepository card) {
        this.repository = card;
    }

    @Override
    public CardEntity save(CardEntity t) {
        return repository.save(t);
    }

    @Override
    public void update(CardEntity t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(CardEntity t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<CardEntity> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Iterable<CardEntity> findAllById() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

}
