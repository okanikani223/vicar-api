package com.github.okanikani223.vicar.share.domain.model.repositories;

import com.github.okanikani223.vicar.share.domain.model.entities.Entity;

import java.util.List;

public interface Repository<ID, E extends Entity<ID>> {
    void save(E entity) throws Exception;

    E getOne(ID id) throws Exception;

    List<E> getAll();

    void update(E entity) throws Exception;

    void deleteOne(ID id) throws Exception;
}
