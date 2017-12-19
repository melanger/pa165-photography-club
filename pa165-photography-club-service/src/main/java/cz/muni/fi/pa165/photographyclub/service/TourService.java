package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;
import java.util.Map;

/**
 * Service for manipulating Tours.
 * @author Pavel Brousek
 */
public interface TourService extends GenericService<Tour> {
    
    /**
     * Method sorts all tours by rating(only according to integer value 
     * of rating values after decimal point are not taken into account). 
     * @return Tours are returned in the form of map where keys are integer 
     * values from 0 to 10 and values are lists of tours with coresponding rating.
     */
    public Map<Integer, List<Tour>> getToursByAverageRating();
    
    public void removeMemberFromAllTours(Long memberId);
    
}
