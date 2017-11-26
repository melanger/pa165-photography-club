package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.ServiceTestApplicationContext;
import cz.muni.fi.pa165.photographyclub.dao.ReviewDao;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** Tests for Review service
 * @author Denis.Figula
 */

@ContextConfiguration(classes = ServiceTestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReviewServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Mock
    private ReviewDao reviewDao;
    
    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Review review = new Review();
    private Review review2 = new Review();

    @BeforeMethod
    public void prepareReview(){
        review.setAuthor(new Member());
        review.setComment("Comment");
        review.setRating(69);
        review.setTour(new Tour());
    }

    @Test
    public void createTest(){
        reviewService.create(review);
        verify(reviewDao, times(1)).create(same(review));
    }

    @Test
    public void findAll(){
        Review review2 = new Review();
        review2.setRating(96);
        List<Review> ReviewList = new LinkedList<>();
        ReviewList.add(review);
        ReviewList.add(review2);
        when(reviewDao.findAll()).thenReturn(ReviewList);
        assertThat(reviewService.findAll()).isSameAs(ReviewList);
    }

    @Test
    public void remove(){
        reviewService.create(review);
        reviewService.remove(review);
        verify(reviewDao, times(1)).remove(same(review));
    }

    @Test
    public void findById(){
        reviewService.create(review);
        when(reviewDao.findById(review.getId())).thenReturn(review);
        assertThat(reviewService.findById(review.getId())).isEqualToComparingFieldByField(review);
    }

    @Test
    public void findByAuthor(){
        Member member = new Member();
        member.setName("Anton");
        review.setAuthor(member);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        review.setAuthor(member);
        when(reviewDao.findByAuthor(review.getAuthor())).thenReturn(reviewList);
        assertThat(reviewService.findByAuthor(review.getAuthor())).isEqualTo(reviewList);
    }

    @Test
    public void findByTour(){
        Tour tour = new Tour();
        tour.setName("SomeTour");
        review.setTour(tour);
        review2.setTour(tour);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(review);
        reviewList.add(review2);
        when(reviewDao.findByTour(review.getTour())).thenReturn(reviewList);
        assertThat(reviewService.findByTour(review.getTour())).isEqualTo(reviewList);
    }

    @Test
    public void getAvgRatingForTour(){
        Tour tour = new Tour();
        tour.setName("SomeTour");
        review.setTour(tour);
        review.setRating(50);
        reviewService.create(review);
        review2.setRating(60);
        review2.setTour(tour);
        reviewService.create(review2);
        assertThat(reviewService.getAverageRatingForTour(review2.getTour())).isEqualTo(55);
    }

    @Test
    public void getAvgRatingForTourNull(){
        Tour tour2 = new Tour();
        tour2.setId(51l);
        tour2.setName("EmptyTour");
        review.setRating(50);
        assertThat(reviewService.getAverageRatingForTour(tour2)).isEqualTo(0);
    }
}
