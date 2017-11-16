package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * @author Pavel Brousek
 */
@Repository
public class ReviewDaoImpl extends GenericDaoImpl<Review> implements ReviewDao {
    @Override
    public Review findById(Long id) {
        return entityManager.find(Review.class, id);
    }

    @Override
    public List<Review> findAll() {
        return entityManager.createQuery("SELECT r FROM Review r", Review.class).getResultList();
    }

    @Override
    public List<Review> findByAuthor(Member m) {
        TypedQuery<Review> query = entityManager.createQuery(
                "Select r from Review r where r.author = :memberid",
                Review.class);

        query.setParameter("memberid", m);
        return query.getResultList();
    }

    @Override
    public List<Review> findByTour(Tour t) {
        TypedQuery<Review> query = entityManager.createQuery(
                "Select r from Review r where r.tour = :tourid",
                Review.class);

        query.setParameter("tourid", t);
        return query.getResultList();
    }
}
