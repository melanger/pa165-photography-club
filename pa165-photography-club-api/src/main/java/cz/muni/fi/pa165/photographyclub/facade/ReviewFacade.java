package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.ReviewCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import java.util.List;

/**
 * Facade interface for functions related to the Review entity.
 * @author Matus Kravec.
 */
public interface ReviewFacade {
    
    /**
     * Method for creating new review.
     * @param review to be created
     */
    public long createReview(ReviewCreateDTO review);
    
    /**
     * Method for deleting review with id same as in id parameter.
     * @param id of the review to be deleted.
     */
    public void removeReview(Long id);
    
    /**
     * Method for updating review.
     * @param review to be updated.
     */
    public void updateReview(ReviewDTO review);
    
    /**
     * Method for getting all reviews.
     * @return list of all reviews.
     */
    public List<ReviewDTO> getAllReviews();
    
    /**
     * Method for finding review by Id.
     * @param id of review to be found.
     * @return found review or null if no such review was found.
     */
    public ReviewDTO getReviewById(Long id);
    
    /**
     * Method for finding all reviews created by member with id specified in authorId parameter.
     * @param authorId Id of the member.
     * @return list of found reviews.
     */
    public List<ReviewDTO> getReviewsByAuthor(Long authorId);
    
    /**
     * Method for finding all reviews associated with tour with id specified in tourId parameter.
     * @param tourId Id of the tour.
     * @return list of found reviews.
     */
    public List<ReviewDTO> getReviewsByTour(Long tourId);
}
