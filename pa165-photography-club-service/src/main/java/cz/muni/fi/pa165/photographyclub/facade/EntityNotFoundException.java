package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.entity.PhotoEntity;

public class EntityNotFoundException extends IllegalArgumentException {
    Class entityClass;
    
    public <T extends PhotoEntity> Class<T> getEntityClass() {
        return entityClass;
    }
    
    public <T extends PhotoEntity> EntityNotFoundException(Class<T> entityClass) {
        super(entityClass.getSimpleName() + " entity not found");
        this.entityClass = entityClass;
    }
}
