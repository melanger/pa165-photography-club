package cz.muni.fi.pa165.photographyclub.service;

import java.util.List;

/**
 * A generic service.
 * @author Pavel Brousek
 */
public interface GenericService<T> {
    /**
     * Create (save) an entity instance.
     * @param e entity instance
     */
    void create(T e);
    
    /**
     * Find an entity instance by id.
     * @param id id of the entity instance
     * @return the entity instance if found, null otherwise
     */
    T findById(Long id);
    
    /**
     * Get a list of all instances of the entity.
     * @return List of entity instances (might be empty)
     */
    List<T> findAll();
    
    /**
     * Remove an entity instance.
     * @param e entity instance to be removed
     */
    void remove(T e);
}
