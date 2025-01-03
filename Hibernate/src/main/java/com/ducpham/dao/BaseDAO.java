package com.ducpham.dao;

import java.util.List;

public interface BaseDAO<E, S> {
    E getById(Long id);

    List<E> getAll();

    List<E> getAll(S search);

    E create(E entity);

    E update(Long id, E entity);

    String delete(Long id);
}
