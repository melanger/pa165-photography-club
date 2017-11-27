package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.FacadeTestApplicationContext;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourDTO;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import cz.muni.fi.pa165.photographyclub.service.ReviewService;
import cz.muni.fi.pa165.photographyclub.service.TourService;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests for TourServiceImpl
 * @author Pavel Brousek
 */
@ContextConfiguration(classes = FacadeTestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReviewFacadeTest extends AbstractTestNGSpringContextTests {
    @Mock
    private ReviewService reviewService;
    
    @Mock
    private MemberService memberService;
    
    @Mock
    private TourService tourService;
    
    @Mock
    private BeanMappingService beanMappingService;
    
    @InjectMocks
    private ReviewFacadeImpl reviewFacade;
    
    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createReviewTest(){
        ReviewCreateDTO r = new ReviewCreateDTO();
        final long authorId = 1l;
        r.setAuthorId(authorId);
        final String comment = "Hello world";
        r.setComment(comment);
        final int rating = 4;
        r.setRating(rating);
        final long tourId = 2l;
        r.setTourId(tourId);
        
        Member m = new Member();
        m.setId(authorId);
        when(memberService.findById(authorId)).thenReturn(m);
        Tour t = new Tour();
        t.setId(tourId);
        when(tourService.findById(tourId)).thenReturn(t);
        
        reviewFacade.createReview(r);
        
        ArgumentCaptor<Review> argumentCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewService, times(1)).create(argumentCaptor.capture());
        Review arg = argumentCaptor.getValue();
        assertThat(arg)
                .hasFieldOrProperty("author")
                .hasFieldOrPropertyWithValue("comment", comment)
                .hasFieldOrPropertyWithValue("rating", rating)
                .hasFieldOrProperty("tour");
        assertThat(arg.getAuthor()).hasFieldOrPropertyWithValue("id", authorId);
        assertThat(arg.getTour()).hasFieldOrPropertyWithValue("id", tourId);
    }
    
    @Test
    public void removeReviewTest() {
        final long reviewId = 42l;
        
        reviewFacade.removeReview(reviewId);
        
        ArgumentCaptor<Review> argumentCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewService, times(1)).remove(argumentCaptor.capture());
        Review arg = argumentCaptor.getValue();
        assertThat(arg).hasFieldOrPropertyWithValue("id", reviewId);
    }
    
    @Test
    public void updateReviewTest() {
        final ReviewDTO r = new ReviewDTO();
        final MemberDTO author = new MemberDTO();
        r.setAuthor(author);
        final String comment = "new comment";
        r.setComment(comment);
        final long reviewId = 42l;
        r.setId(reviewId);
        final int rating = 0;
        r.setRating(rating);
        final TourDTO tour = new TourDTO();
        tour.setId(1l);
        r.setTour(tour);
        
        Review mockReview = mock(Review.class);
        when(reviewService.findById(reviewId)).thenReturn(mockReview);
        
        reviewFacade.updateReview(r);
        
        verify(mockReview).setAuthor(any(Member.class));
        verify(mockReview).setComment(comment);
        verify(mockReview).setRating(rating);
        verify(mockReview).setTour(any(Tour.class));
        verify(mockReview, never()).setId(any(Long.class));
    }
    
    @Test
    public void getAllReviewsTest() {
        final List<Review> reviews = new LinkedList<>();
        reviews.add(new Review());
        final List<ReviewDTO> reviewDTOs = new LinkedList<>();
        reviewDTOs.add(new ReviewDTO());
        when(reviewService.findAll()).thenReturn(reviews);
        when(beanMappingService.mapTo(reviews, ReviewDTO.class)).thenReturn(reviewDTOs);
        
        assertThat(reviewFacade.getAllReviews()).isEqualTo(reviewDTOs);
    }
    
    @Test
    public void getReviewById() {
        final long reviewId = 42l;
        final Review review = new Review();
        final ReviewDTO reviewDTO = new ReviewDTO();
        review.setId(reviewId);
        when(reviewService.findById(reviewId)).thenReturn(review);
        when(beanMappingService.mapTo(review, ReviewDTO.class)).thenReturn(reviewDTO);
        
        assertThat(reviewFacade.getReviewById(reviewId)).isEqualTo(reviewDTO);
    }
    
    @Test
    public void getReviewByAuthor() {
        final long authorId = 42l;
        final Member author = new Member();
        author.setId(authorId);
        final Review r1 = new Review();
        r1.setAuthor(author);
        final Review r2 = new Review();
        r2.setAuthor(author);
        List<Review> reviews = new LinkedList<>();
        reviews.add(r1);
        reviews.add(r2);
        List<ReviewDTO> reviewDTOs = new LinkedList<>();
        final ReviewDTO dto1 = new ReviewDTO();
        dto1.setId(1l);
        final ReviewDTO dto2 = new ReviewDTO();
        dto2.setId(2l);
        reviewDTOs.add(dto1);
        reviewDTOs.add(dto2);
        
        when(memberService.findById(authorId)).thenReturn(author);
        when(reviewService.findByAuthor(author)).thenReturn(reviews);
        when(beanMappingService.mapTo(reviews, ReviewDTO.class)).thenReturn(reviewDTOs);
        
        
        assertThat(reviewFacade.getReviewsByAuthor(authorId)).isEqualTo(reviewDTOs);
    }
    
    @Test
    public void getReviewByTour() {
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
        
        
        assertThat(reviewFacade.getReviewsByTour(tourId)).isEqualTo(reviewDTOs);
    }
}
