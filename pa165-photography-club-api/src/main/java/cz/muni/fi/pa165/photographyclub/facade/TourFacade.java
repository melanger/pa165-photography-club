package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourDTO;
import java.util.List;

/**
 *
 * @author Matus Kravec.
 */
public interface TourFacade {
    
    public List<TourDTO> getAllTours();
    
    public TourDTO getTourById(Long id);
    
    public void createTour(TourCreateDTO tour);
    
    public void removeTour(Long id);
    
    public List<ReviewDTO> getTourReviews();
    
    public List<MemberDTO> getTourParticipants();
    
    public double getTourRating();
}
