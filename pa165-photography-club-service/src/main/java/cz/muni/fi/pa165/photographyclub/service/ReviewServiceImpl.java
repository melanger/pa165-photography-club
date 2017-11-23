package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.ReviewDao;
import cz.muni.fi.pa165.photographyclub.dao.TourDao;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.inject.Inject;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    @Inject
    ReviewDao reviewDao;

    @Override
    public void create(Review e) {
        reviewDao.create(e);
    }

    @Override
    public Review findById(Long id) {
        return reviewDao.findById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    @Override
    public void remove(Review e) {
        reviewDao.remove(e);
    }

    @Override
    public List<Review> findReviewByAuthor(Member m) {
        return reviewDao.findByAuthor(m);
    }

    @Override
    public List<Review> findReviewByTour(Tour t) {
        return reviewDao.findByTour(t);
    }

    @Override
    public double getAverageRatingForTour(Tour t) {
        List<Review> reviewList = findReviewByTour(t);
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
