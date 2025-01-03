package com.ducpham.service;

import java.util.List;

public interface IStudentService<T, S> {
    T getById(Long id);

    List<T> getAll();

    List<T> getAll(S search);

    T create(T request);

    T update(Long id, T request);

    String delete(Long id);


}
