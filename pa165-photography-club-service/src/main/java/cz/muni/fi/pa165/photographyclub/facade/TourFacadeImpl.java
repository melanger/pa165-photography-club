package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourDTO;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.service.ReviewService;
import cz.muni.fi.pa165.photographyclub.service.TourService;

import javax.inject.Inject;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**Class implementing tour facade
 * @author Denis.Figula
 */
@Transactional
@Service
public class TourFacadeImpl implements TourFacade {

    @Inject
    private ReviewService reviewService;

    @Inject
    private TourService tourService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public List<TourDTO> getAllTours() {
        return beanMappingService.mapTo(tourService.findAll(),TourDTO.class);
    }

    @Override
    public TourDTO getTourById(Long id) {
        Tour t = tourService.findById(id);
        return (t == null) ? null : beanMappingService.mapTo(t,TourDTO.class);
    }

    @Override
    public long createTour(TourCreateDTO tour) {
        Tour t = new Tour();
        t.setName(tour.getName());
        t.setDate(tour.getDate());
        t.setTheme(tour.getTheme());
        tourService.create(t);
        t.setReviews(reviewService.findByTour(t));
        return t.getId();
    }

    @Override
    public void removeTour(Long id) {
        Tour tour = tourService.findById(id);
        if (tour == null) throw new EntityNotFoundException();
        tourService.remove(tour);
    }

    @Override
    public List<ReviewDTO> getTourReviews(Long tourId) {
        Tour tour = tourService.findById(tourId);
        if (tour == null) throw new EntityNotFoundException();
        List<Review> reviews = reviewService.findByTour(tour);
        return beanMappingService.mapTo(reviews,ReviewDTO.class);
    }

    @Override
    public List<MemberDTO> getTourParticipants(Long tourId) {
        Tour tour = tourService.findById(tourId);
        if (tour == null) throw new EntityNotFoundException();
        List<Member> participants = tour.getParticipants();
        return beanMappingService.mapTo(participants, MemberDTO.class);
    }

    @Override
    public double getTourRating(Long tourId) {
        Tour tour = tourService.findById(tourId);
        if (tour == null) throw new EntityNotFoundException();
        return reviewService.getAverageRatingForTour(tour);
    }
}
