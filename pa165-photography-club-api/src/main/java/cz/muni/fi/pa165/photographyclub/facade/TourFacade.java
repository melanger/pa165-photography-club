package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourDTO;
import java.util.List;

/**
 * Facade interface for functions related to the Tour entity.
 * @author Matus Kravec. 
 */
public interface TourFacade {
    
    /**
     * Method for getting list of all tours.
     * @return list of all tours.
     */
    public List<TourDTO> getAllTours();
    
    /**
     * Method for finding Tour with same id as specified in id parameter.
     * @param id oh tour to be found.
     * @return found tour or null if no such tour was found.
     */
    public TourDTO getTourById(Long id);
    
    /**
     * Method for creating tour.
     * @param tour to be created.
     */
    public void createTour(TourCreateDTO tour);
    
    /**
     * Method for deleting tour.
     * @param id of the tour to be deleted.
     */
    public void removeTour(Long id);
    
    /**
     * Method for getting all reviews for tour with sam id as specified in tourId parameter.
     * @param tourId id of the tour.
     * @return list of found reviews.
     */
    public List<ReviewDTO> getTourReviews(Long tourId);
    
    /**
     * Method for getting list of all tour participants. Tour is specified by tourId parameter.
     * @param tourId id of the tour.
     * @return list of all members participating in specified tour.
     */
    public List<MemberDTO> getTourParticipants(Long tourId);
    
    /**
     * Method for calculating rating of the tour from all reviews.
     * @param tourId id of the tour.
     * @return average value of rating from all reviews.
     */
    public double getTourRating(Long tourId);
}
