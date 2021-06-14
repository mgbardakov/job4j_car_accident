package ru.job4j.accident.repository.mem;

import ru.job4j.accident.model.BaseEntity;
import ru.job4j.accident.repository.CRUDRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MemCRUDRepository<T extends BaseEntity> implements CRUDRepository<T> {
    protected final Map<Integer, T> repository = new HashMap<>();
    private final AtomicInteger id = new AtomicInteger(1);

    @Override
    public T create(T model) {
        if (!repository.containsKey(model.getId())) {
            model.setId(id.getAndIncrement());
        }
        repository.put(model.getId(), model);
        return model;
    }

    @Override
    public T read(Class<T> type, Integer id) {
        return repository.get(id);
    }

    @Override
    public void update(T model) {
        repository.put(model.getId(), model);
    }

    @Override
    public void delete(T model) {
        repository.remove(model.getId());
    }

    @Override
    public List<T> findAll(Class<T> type) {
        var rslList = new ArrayList<T>(repository.values());
        rslList.sort(Comparator.comparing(BaseEntity::getId));
        return rslList;
    }


}
