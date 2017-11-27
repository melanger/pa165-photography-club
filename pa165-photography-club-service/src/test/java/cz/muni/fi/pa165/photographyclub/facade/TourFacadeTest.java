package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.FacadeTestApplicationContext;
import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourDTO;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import cz.muni.fi.pa165.photographyclub.service.ReviewService;
import cz.muni.fi.pa165.photographyclub.service.TourService;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Denis.Figula
 */
@ContextConfiguration(classes = FacadeTestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TourFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ReviewService reviewService;

    @Mock
    private MemberService memberService;

    @Mock
    private TourService tourService;

    @Mock
    private BeanMappingService beanMappingService;

    @InjectMocks
    private TourFacadeImpl tourFacade;

    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    private void createTourTest(){
        TourCreateDTO t = new TourCreateDTO();
        final String name = "Tour de France";
        t.setName(name);
        final LocalDate date = LocalDate.of(2017,01,01);
        t.setDate(LocalDate.of(2017,01,01));
        t.setTheme(TourTheme.LANDSCAPE);


        tourFacade.createTour(t);

        ArgumentCaptor<Tour> argumentCaptor = ArgumentCaptor.forClass(Tour.class);
        verify(tourService, times(1)).create(argumentCaptor.capture());
        Tour arg = argumentCaptor.getValue();
        assertThat(arg)
                .hasFieldOrPropertyWithValue("name",name)
                .hasFieldOrPropertyWithValue("date", date)
                .hasFieldOrProperty("theme");
    }

    @Test
    private void removeTourTest(){
        final long tourId = 42l;

        tourFacade.removeTour(tourId);

        ArgumentCaptor<Tour> argumentCaptor = ArgumentCaptor.forClass(Tour.class);
        verify(tourService, times(1)).remove(argumentCaptor.capture());
        Tour arg = argumentCaptor.getValue();
        assertThat(arg).hasFieldOrPropertyWithValue("id", tourId);
    }

    @Test
    public void getAllToursTest() {
        final List<Tour> tours = new LinkedList<>();
        tours.add(new Tour());
        final List<TourDTO> tourDTOs = new LinkedList<>();
        tourDTOs.add(new TourDTO());
        when(tourService.findAll()).thenReturn(tours);
        when(beanMappingService.mapTo(tours, TourDTO.class)).thenReturn(tourDTOs);

        assertThat(tourFacade.getAllTours()).isEqualTo(tourDTOs);
    }

    @Test
    public void getTourById() {
        final long tourId = 113l;
        final Tour tour = new Tour();
        final TourDTO tourDTO = new TourDTO();
        tour.setId(tourId);
        when(tourService.findById(tourId)).thenReturn(tour);
        when(beanMappingService.mapTo(tour, TourDTO.class)).thenReturn(tourDTO);

        assertThat(tourFacade.getTourById(tourId)).isEqualTo(tourDTO);
    }

    @Test
    public void getTourReviewsTest(){
//        final Tour tour = new Tour();
//        tour.setId(45l);
//        final Review review1 = new Review();
//        review1.setRating(50);
//        review1.setId(23l);
//        review1.setTour(tour);
//        final Review review2 = new Review();
//        review2.setRating(60);
//        review2.setId(32l);
//        review2.setTour(tour);
//        final List<Review> reviews = new ArrayList<>();
//        reviews.add(review1);
//        reviews.add(review2);
//        tour.setReviews(reviews);
//        when(tourService.findById(tour.getId())).thenReturn(tour);
//        when(reviewService.findByTour(tour)).thenReturn(reviews);
//        assertThat(tourFacade.getTourReviews(tour.getId())).isEqualTo(reviews);
        final long tourId = 42l;
        final Tour tour = new Tour();
        tour.setId(tourId);
        final Review r1 = new Review();
        r1.setTour(tour);
        final Review r2 = new Review();
        r2.setTour(tour);
        List<Review> reviews = new LinkedList<>();
        reviews.add(r1);
        reviews.add(r2);
        tour.setReviews(reviews);
        List<ReviewDTO> reviewDTOs = new LinkedList<>();
        final ReviewDTO dto1 = new ReviewDTO();
        dto1.setId(1l);
        final ReviewDTO dto2 = new ReviewDTO();
        dto2.setId(2l);
        reviewDTOs.add(dto1);
        reviewDTOs.add(dto2);

        when(tourService.findById(tourId)).thenReturn(tour);
        when(reviewService.findByTour(tour)).thenReturn(reviews);
        when(beanMappingService.mapTo(reviews, ReviewDTO.class)).thenReturn(reviewDTOs);

        assertThat(tourFacade.getTourReviews(tour.getId())).isEqualTo(reviewDTOs);
    }

    @Test
    public void getTourRatingTest(){
        final Tour tour1 = new Tour();
        final Long tourId = 33l;
        tour1.setId(tourId);
        final Review review1 = new Review();
        review1.setRating(50);
        final Review review2 = new Review();
        review2.setRating(60);
        final List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        tour1.setReviews(reviews);
        when(tourService.findById(tourId)).thenReturn(tour1);
        when(reviewService.getAverageRatingForTour(tour1)).thenReturn(55d);
        assertThat(tourFacade.getTourRating(tourId)).isEqualTo(55);
    }
}
