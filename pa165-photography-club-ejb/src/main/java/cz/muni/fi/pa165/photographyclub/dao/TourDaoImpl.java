package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Tour;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * @author Denis.Figula
 *
 * Data access object implementation for Tour entity
 */
@Repository
public class TourDaoImpl implements TourDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createTour(Tour tour) {
        entityManager.persist(tour);
    }

    @Override
    public void removeTour(Tour tour) {
        entityManager.remove(entityManager.merge(tour));
    }

    @Override
    public void updateTour(Tour tour) {
        entityManager.merge(tour);
    }

    @Override
    public List<Tour> getAllTours() {
        return entityManager.createQuery("select t from Tour t", Tour.class).getResultList();
    }

    @Override
    public Tour getTourByID(Long id) {
        return entityManager.find(Tour.class, id);
    }

    @Override
    public Tour getTourByName(String name) {
        Tour result = null;
        try {
            result = entityManager.createQuery("select t from Tour t where t.name = :name", Tour.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return result;
    }
}
