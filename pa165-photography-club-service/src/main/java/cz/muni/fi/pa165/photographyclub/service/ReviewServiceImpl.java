package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.GenericDao;
import cz.muni.fi.pa165.photographyclub.dao.ReviewDao;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl extends GenericServiceImpl<Review> implements ReviewService {
    
    @Inject
    private ReviewDao reviewDao;
    
    @Override
    protected GenericDao<Review> getDao() {
        return reviewDao;
    }

    @Override
    public List<Review> findByAuthor(Member m) {
        return reviewDao.findByAuthor(m);
    }

    @Override
    public List<Review> findByTour(Tour t) {
        return reviewDao.findByTour(t);
    }

    @Override
    public double getAverageRatingForTour(Tour t) {
        List<Review> reviewList = reviewDao.findByTour(t);
        if (reviewList.isEmpty()){
            return 0;
        }
        double avgRating = 0;
        for (Review r : reviewList){
            avgRating += r.getRating();
        }
        avgRating = avgRating/reviewList.size();
        return avgRating;
    }
}
