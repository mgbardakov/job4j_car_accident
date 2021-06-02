package ru.job4j.accident.repository;

import java.util.List;

public interface CRUDRepository <T> {
    T create(T model);
    T read(Class<T> type, Integer id);
    void update (T model);
    void delete (T model);
    List<T> findAll(final Class<T> type);
}
