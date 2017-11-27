package cz.muni.fi.pa165.photographyclub.beanmapping;

import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

@Service
public class BeanMappingServiceImpl implements BeanMappingService {
	
    @Inject
    private Mapper dozer;

    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public  <T> T mapTo(Object u, Class<T> mapToClass)
    {
        return dozer.map(u,mapToClass);
    }
    
    public Mapper getMapper(){
    	return dozer;
    }
}