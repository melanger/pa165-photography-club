package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;

import java.util.List;

/**
 * @author Denis.Figula
 */
public interface ReviewService extends GenericService<Review> {
    /**
     * searching review by author
     * @param m author of reviews
     * @return list of reviews
     */
    List<Review> findByAuthor(Member m);

    /**
     * searching reviews by tour
     * @param t tour parameter
     * @return list of reviews
     */
    List<Review> findByTour(Tour t);

    /**
     * getting average rating for tour
     * @param t tour
     * @return average score of tour
     */
    double getAverageRatingForTour(Tour t);
}
