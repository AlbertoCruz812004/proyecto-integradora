package com.allstock.app.services;

import java.util.Optional;

public interface CrudService<T,ID> {

    T save(T t);

    void update(T t);

    boolean delete(T t);

    Optional<T> findById(ID id);

    Iterable<T> findAllById();
}
