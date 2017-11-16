package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;

/**
 * @author Pavel Brousek
 */
public interface ReviewDao {
    /**
     * Retrieve a review based on id.
     * @param id review id
     * @return review if found, null otherwise
     */
    public Review findById(Long id);
    /**
     * Create (store) a new review.
     * @param r review to be stored
     */
    public void create(Review r);
    /**
     * Remove (delete) a review.
     * @param r review to be deleted
     */
    public void remove(Review r);
    /**
     * Get all reviews.
     * @return list of reviews (might be empty)
     */
    public List<Review> findAll();
    /**
     * Get reviews of a member.
     * @param m member whose reviews should be fetched
     * @return list of reviews of the member (might be empty)
     */
    public List<Review> findByAuthor(Member m);
    /**
     * Get reviews of a tour.
     * @param t tour for which reviews should be fetched
     * @return list of reviews for the tour (might be empty)
     */
    public List<Review> findByTour(Tour t);
}
