package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.ReviewDao;
import cz.muni.fi.pa165.photographyclub.entity.Review;

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
}
