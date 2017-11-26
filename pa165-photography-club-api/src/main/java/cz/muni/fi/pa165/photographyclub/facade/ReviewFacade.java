package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.ReviewCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import java.util.List;

/**
 *
 * @author Matus Kravec.
 */
public interface ReviewFacade {
    
    public void createReview(ReviewCreateDTO review);
    
    public void removeReview(Long id);
    
    public void updateReview(ReviewDTO review);
    
    public List<ReviewDTO> getAllReviews();
    
    public ReviewDTO getReviewById(Long id);
    
    public List<ReviewDTO> getReviewsByAuthor(Long authorId);
    
    public List<ReviewDTO> getReviewsByTour(Long tourId);
}
