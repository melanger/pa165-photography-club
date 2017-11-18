package cz.muni.fi.pa165.photographyclub.dao;

import java.util.List;

/**
 * Generic interface for a DAO (data access object).
 * @author Pavel Brousek
 * @param <T> type of entity
 */
public interface GenericDao<T> {
    /**
     * Create (store) a new entity.
     * @param e entity to be stored
     */
    void create(T e);
    
    /**
     * Get all entities.
     * @return list of entities (might be empty)
     */
    List<T> findAll();
    /**
     * Retrieve an entity based on id.
     * @param id entity id
     * @return entity if found, null otherwise
     */
    T findById(Long id);
    
    /**
     * Update an existing entity.
     * @param e entity to be updated
     */
    void update(T e);
    
    /**
     * Remove (delete) an entity.
     * @param e entity to be deleted
     */
    void remove(T e);
}
