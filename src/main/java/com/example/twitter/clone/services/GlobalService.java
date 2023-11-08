package com.example.twitter.clone.services;

import java.util.List;
import java.util.Objects;

public interface GlobalService<Entity> {
    public List<Entity> findAll() throws Exception;

    public Entity findById(Long id) throws Exception;

    public Entity save(Entity entity) throws Exception;

    public Entity update(Long id, Entity entity) throws Exception;

    public Entity delete(Long id) throws Exception;
}
