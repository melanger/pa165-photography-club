package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.entity.Tour;

/**
 * @author Denis.Figula
 *
 * Data access object interface for tour entity
 */
public interface TourDao extends GenericDao<Tour> {
    /**
     * Search for a tour with the given name
     * @param name name of the searched tour
     * @return found tour, null otherwise
     */
    Tour findByName(String name);
}
