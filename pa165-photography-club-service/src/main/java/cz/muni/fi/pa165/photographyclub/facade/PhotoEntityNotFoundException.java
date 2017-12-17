package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.entity.PhotoEntity;
import javax.persistence.EntityNotFoundException;

public class PhotoEntityNotFoundException extends EntityNotFoundException {
    Class entityClass;
    
    public <T extends PhotoEntity> Class<T> getEntityClass() {
        return entityClass;
    }
    
    public <T extends PhotoEntity> PhotoEntityNotFoundException(Class<T> entityClass) {
        super(entityClass.getSimpleName() + " entity not found");
        this.entityClass = entityClass;
    }
}
