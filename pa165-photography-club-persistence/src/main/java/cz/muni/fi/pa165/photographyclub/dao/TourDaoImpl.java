package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * @author Denis.Figula
 *
 * Data access object implementation for Tour entity
 */
@Repository
public class TourDaoImpl extends GenericDaoImpl<Tour> implements TourDao {
    @Override
    public List<Tour> findAll() {
        return entityManager.createQuery("select t from Tour t", Tour.class).getResultList();
    }

    @Override
    public Tour findById(Long id) {
        return entityManager.find(Tour.class, id);
    }

    @Override
    public Tour findByName(String name) {
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
