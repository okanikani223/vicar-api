package com.github.okanikani223.vicar.share.domain.model.entities;

import java.util.Objects;


public abstract class Entity<ID> {
    private final ID id;

    protected Entity(ID id) {
        this.id = id;
    }

    protected ID id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
