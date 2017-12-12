package cz.muni.fi.pa165.photographyclub.beanmapping;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;


public interface BeanMappingService {
    /**
     * Map a collection of objects to a list of objects of the mapToClass.
     * @param <T> the target type
     * @param objects collection of source objects
     * @param mapToClass target class
     * @return list of mapped objects
     */
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    
    /**
     * Map an object to an object of the mapToClass.
     * @param <T> the target type
     * @param u source object
     * @param mapToClass target class
     * @return the mapped object
     */
    public  <T> T mapTo(Object u, Class<T> mapToClass);
    
    /**
     * Getter for mapper.
     * @return Mapper
     */
    public Mapper getMapper();
}