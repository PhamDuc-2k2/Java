package com.ducpham.dao;

import java.util.List;

public interface IStudentDAO<E, S> {
    E getById(Long id);

    List<E> getAll();

    List<E> getAll(S search);

    E create(E request);

    E update(Long id, E request);

    String delete(Long id);

    boolean existsByCode(String code);

    boolean existsByCodeAndNotId(Long id, String code);
}
