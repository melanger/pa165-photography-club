package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.GenericDao;
import cz.muni.fi.pa165.photographyclub.dao.TourDao;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * Implementation of TourService.
 * @author Pavel Brousek
 */
@Service
public class TourServiceImpl extends GenericServiceImpl<Tour> implements TourService {
    @Inject
    private TourDao tourDao;

    @Override
    protected GenericDao<Tour> getDao() {
        return tourDao;
    }
    
    @Override
    public Map<Integer, List<Tour>> getToursByAverageRating(){
        Map<Integer, List<Tour>> map = new HashMap<>();
        List<Tour> tours = tourDao.findAll();
        for (int i = 0; i <= 10; i++) {
            map.put(i, new LinkedList<Tour>());
        }
        for (Tour tour : tours) {
            List<Review> reviews = tour.getReviews();
            double avg = 0;
            for (Review review : reviews) {
                avg += review.getRating();
            }
            avg = avg / reviews.size();
            avg = Math.floor(avg);
            int avgInt = (int) avg;
            List<Tour> tmpList = map.get(avgInt);
            tmpList.add(tour);
            map.replace(avgInt, tmpList);
        }
        return map;
    }
}
