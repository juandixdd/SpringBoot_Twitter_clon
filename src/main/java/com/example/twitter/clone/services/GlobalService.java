package com.example.twitter.clone.services;

import java.util.List;

public interface GlobalService<Entity> {
    public List<Entity> findAll() throws Exception;

    public Entity findById(Long id) throws Exception;

    public Entity save(Entity entity) throws Exception;

    public Entity update(Long id, Entity entity) throws Exception;

    public boolean delete(Long id) throws Exception;
}
