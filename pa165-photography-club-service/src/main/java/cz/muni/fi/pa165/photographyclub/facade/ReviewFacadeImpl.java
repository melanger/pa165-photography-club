package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.ReviewCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import cz.muni.fi.pa165.photographyclub.service.ReviewService;
import cz.muni.fi.pa165.photographyclub.service.TourService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Implementation of ReviewFacade
 * @author Pavel Brousek
 */
@Transactional
@Service
public class ReviewFacadeImpl implements ReviewFacade {
    @Inject
    private ReviewService reviewService;
    @Inject
    private MemberService memberService;
    @Inject
    private TourService tourService;
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public void createReview(ReviewCreateDTO review) {
        Review r = new Review();
        r.setAuthor(memberService.findById(review.getAuthorId()));
        r.setComment(review.getComment());
        r.setRating(review.getRating());
        r.setTour(tourService.findById(review.getTourId()));
        reviewService.create(r);
    }

    @Override
    public void removeReview(Long id) {
        Review r = new Review();
        r.setId(id);
        reviewService.remove(r);
    }

    @Override
    public void updateReview(ReviewDTO review) {
        Review r = reviewService.findById(review.getId());
        if (review.getAuthor() == null) {
            r.setAuthor(null);
        } else {
            r.setAuthor(beanMappingService.mapTo(review.getAuthor(), Member.class));
        }
        r.setComment(review.getComment());
        r.setRating(review.getRating());
        if (review.getTour() == null) {
            r.setTour(null);
        } else {
            r.setTour(beanMappingService.mapTo(review.getTour(), Tour.class));
        }
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return beanMappingService.mapTo(reviewService.findAll(), ReviewDTO.class);
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review r = reviewService.findById(id);
        return (r == null) ? null : beanMappingService.mapTo(r, ReviewDTO.class);
    }

    @Override
    public List<ReviewDTO> getReviewsByAuthor(Long authorId) {
        List<Review> reviews = reviewService.findByAuthor(memberService.findById(authorId));
        return beanMappingService.mapTo(reviews, ReviewDTO.class);
    }

    @Override
    public List<ReviewDTO> getReviewsByTour(Long tourId) {
        List<Review> reviews = reviewService.findByTour(tourService.findById(tourId));
        return beanMappingService.mapTo(reviews, ReviewDTO.class);
    }
    
}
