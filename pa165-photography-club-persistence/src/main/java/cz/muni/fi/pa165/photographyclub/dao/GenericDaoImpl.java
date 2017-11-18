package cz.muni.fi.pa165.photographyclub.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Generic DAO implementation.
 * @author Pavel Brousek
 * @param <T> type of entity
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    @PersistenceContext
    protected EntityManager entityManager;
    
    @Override
    public void create(T e) {
        entityManager.persist(e);
    }
    
    @Override
    public void update(T e) {
        entityManager.merge(e);
    }
    
    @Override
    public void remove(T e) {
        entityManager.remove(entityManager.merge(e));
    }
}
