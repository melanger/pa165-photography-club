package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;

import java.util.List;

public interface ReviewService extends GenericService<Review> {

    List<Review> findByAuthor(Member m);

    List<Review> findByTour(Tour t);

    double getAverageRatingForTour(Tour t);
}
