package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;
import java.util.Map;

/**
 * Service for manipulating Tours.
 * @author Pavel Brousek
 */
public interface TourService extends GenericService<Tour> {
    
    public Map<Integer, List<Tour>> getToursByRating();
    
}
