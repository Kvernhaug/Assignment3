package com.example.assignment3.services;

import java.util.Collection;

/**
 * Service interface for performing generic CRUD actions.
 * @param <T> Object class to perform CRUD actions on
 * @param <ID> ID of specific class entity
 */
public interface CrudService <T, ID> {

    /**
     * Get specific entity by ID.
     * @param id ID of entity
     * @return the requested entity
     */
    T findById(ID id);

    /**
     * Get all entities of class T.
     * @return collection of all found entities
     */
    Collection<T> findAll();

    /**
     * Add new entity of class T.
     * @param entity Entity to be added
     * @return entity added to database
     */
    T add(T entity);

    /**
     * Update existing entity of class T.
     * @param entity Entity to be updated
     * @return updated entity in database
     */
    T update(T entity);


    void deleteById(ID id);
    void delete(T entity);
}
