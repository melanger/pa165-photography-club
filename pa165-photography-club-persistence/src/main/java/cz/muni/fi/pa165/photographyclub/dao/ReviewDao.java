package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;

/**
 * @author Pavel Brousek
 */
public interface ReviewDao extends GenericDao<Review> {
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
