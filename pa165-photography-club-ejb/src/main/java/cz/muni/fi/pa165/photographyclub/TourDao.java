package cz.muni.fi.pa165.photographyclub;

import java.util.List;

/**
 * @author Denis.Figula
 *
 * Data access object interface for tour entity
 */
public interface TourDao {
    /**
     * This metod handles adding tour
     * @param tour new tour to be added
     */
    void createTour(Tour tour);

    /**
     * This method handles deleting tour
     * @param tour tour to be deleted
     */
    void removeTour(Tour tour);

    /**
     * This method handles updating saved tour
     * @param tour tour to be updated
     */
    void updateTour(Tour tour);

    /**
     * This method searches for all tours
     * @return list of all available tours
     */
    List<Tour> getAllTours();

    /**
     * This method finds single tour
     * @param id id of searched tour
     * @return found tour
     */
    Tour getTourByID(Long id);

    /**
     * This method search for tour with given name
     * @param name name of searched tour
     * @return found tour
     */
    Tour getTourByName(String name);
}
