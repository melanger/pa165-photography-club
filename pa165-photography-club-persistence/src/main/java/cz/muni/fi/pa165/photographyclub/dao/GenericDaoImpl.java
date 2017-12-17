package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.PhotoEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Generic DAO implementation.
 * @author Pavel Brousek
 * @param <T> type of entity
 */
@Repository
public abstract class GenericDaoImpl<T extends PhotoEntity> implements GenericDao<T> {
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
        if (this.findById(e.getId()) == null)
            throw new IllegalArgumentException("Entity to be deleted not found");
        entityManager.remove(entityManager.merge(e));
    }
}
