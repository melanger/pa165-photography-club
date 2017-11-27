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
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
    
    private ReviewDTO r1;
    
    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
        //reviewFacade = new ReviewFacadeImpl(reviewService, memberService, tourService, beanMappingService);
    }
    
    @BeforeMethod
    public void setupMethod(){
        r1 = new ReviewDTO();
        r1.setAuthor(new MemberDTO());
        r1.setComment("Hello world");
        r1.setRating(4);
        r1.setTour(new TourDTO());
        r1.setId(42l);
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
    
}
