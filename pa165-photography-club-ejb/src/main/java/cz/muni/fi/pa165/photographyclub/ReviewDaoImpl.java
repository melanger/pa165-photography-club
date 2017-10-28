package cz.muni.fi.pa165.photographyclub;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author Pavel Brousek
 */
public class ReviewDaoImpl implements ReviewDao {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Review findById(Long id) {
        return em.find(Review.class, id);
    }

    @Override
    public void create(Review r) {
        em.persist(r);
    }

    @Override
    public void remove(Review r) {
        em.remove(r);
    }

    @Override
    public List<Review> findAll() {
        return em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
    }

    @Override
    public List<Review> findByAuthor(Member m) {
        TypedQuery<Review> query = em.createQuery(
				"Select r from Review r where r.author = :memberid",
				Review.class);
		
		query.setParameter("memberid", m);
        return query.getResultList();
    }

    @Override
    public List<Review> findByTour(Tour t) {
        TypedQuery<Review> query = em.createQuery(
				"Select r from Review r where r.tour = :tourid",
				Review.class);
		
		query.setParameter("tourid", t);
        return query.getResultList();
    }    
}