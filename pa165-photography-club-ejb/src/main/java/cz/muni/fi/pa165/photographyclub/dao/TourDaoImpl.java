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

    /**
     * This metod handles adding tour
     * @param tour new tour to be added
     */
    @Override
    public void createTour(Tour tour) {
        entityManager.persist(tour);
    }

    /**
     * This method handles deleting tour
     * @param tour tour to be deleted
     */
    @Override
    public void removeTour(Tour tour) {
        entityManager.remove(entityManager.merge(tour));
    }

    /**
     * This method handles updating saved tour
     * @param tour tour to be updated
     */
    @Override
    public void updateTour(Tour tour) {
        entityManager.merge(tour);
    }

    /**
     * This method searches for all tours
     * @return list of all tours
     */
    @Override
    public List<Tour> getAllTours() {
        return entityManager.createQuery("select t from Tour t", Tour.class).getResultList();
    }

    /**
     * This method finds single tour
     * @param id id of searched tour
     * @return found tour
     */
    @Override
    public Tour getTourByID(Long id) {
        return entityManager.find(Tour.class,id);
    }

    /**
     * This method search for tour with given name
     * @param name name of searched tour
     * @return found tour
     */
    @Override
    public Tour getTourByName(String name) {
        Tour result = null;
        try{
        result = entityManager.createQuery("select t from Tour t where t.name = :name", Tour.class)
                .setParameter("name",name).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
        return result;
    }
}
