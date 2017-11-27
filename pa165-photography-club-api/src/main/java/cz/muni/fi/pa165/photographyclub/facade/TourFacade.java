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
    
    public List<TourDTO> getAllTours();
    
    public TourDTO getTourById(Long id);
    
    public void createTour(TourCreateDTO tour);
    
    public void removeTour(Long id);
    
    public List<ReviewDTO> getTourReviews(Long tourId);
    
    public List<MemberDTO> getTourParticipants(Long tourId);
    
    public double getTourRating(Long tourId);
}
