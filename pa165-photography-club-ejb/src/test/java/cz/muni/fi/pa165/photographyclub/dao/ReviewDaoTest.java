package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import cz.muni.fi.pa165.photographyclub.service.ServiceImpl;
import java.sql.Date;
import java.time.Instant;
import javax.validation.ConstraintViolationException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.TransactionSystemException;

/**
 * @author Pavel Brousek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class ReviewDaoTest extends AbstractTestNGSpringContextTests {
    
    /*@Autowired
    private ReviewDao reviewDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private TourDao tourDao;   */
    
    @Autowired
    private ServiceImpl service;
    
    private static final String DEFAULT_COMMENT = "1";
    
    private Member createFullMember(){
        Member author = new Member();
        author.setName("Karel");
        author.setBirthDate(new Date(2017, 10, 28));
        //memberDao.create(author);
        service.createMember(author);
        return author;
    }
    private Tour createFullTour(){
        Tour tour = new Tour();
        tour.setName("Landscape 2017");
        tour.setTheme(TourTheme.LANDSCAPE);
        tour.setDate(java.util.Date.from(Instant.now()));
        //tourDao.createTour(tour);
        service.createTour(tour);
        return tour;
    }
    
    private Review createFullReview() {
        Member author = createFullMember();
        Tour tour = createFullTour();
        Review review = new Review();
        review.setAuthor(author);
        review.setTour(tour);
        review.setComment(DEFAULT_COMMENT);
        //reviewDao.create(review);
        service.createReview(review);
        return review;
    }
    
    @Test   //asserting exception causes database TransactionSystemException.
    public void nullReviewCommentNotAllowed(){
        final Review review = new Review();
        review.setComment(null);
        assertThatThrownBy(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                //reviewDao.create(review);
                service.createReview(review);
            }
        }).isInstanceOf(TransactionSystemException.class);  //.isInstanceOf(ConstraintViolationException.class);
    }
    
    @Test
    public void authorTourCombinationIsUnique(){
        Review review1 = createFullReview();

        final Review review2 = new Review();
        review2.setAuthor(createFullMember());
        review2.setTour(createFullTour());
        review2.setComment("2");

        assertThatThrownBy(new ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                //reviewDao.create(review2);
                service.createReview(review2);
            }
        }).isInstanceOf(DataAccessException.class);  //.isInstanceOf(DataAccessException.class);
    }
    
    @Test()
    public void savesComment(){
        Review review = createFullReview();
        
        assertThat(review.getComment()).isEqualTo(DEFAULT_COMMENT);
    }
    
    @Test()
    public void delete(){
        Review review = createFullReview();
        /*assertThat(reviewDao.findById(review.getId())).isNotNull();
        reviewDao.remove(review);
        assertThat(reviewDao.findById(review.getId())).isNull();*/
        assertThat(service.findReviewById(review.getId())).isNotNull();
        service.removeReview(review);
        assertThat(service.findReviewById(review.getId())).isNull();
    }
}