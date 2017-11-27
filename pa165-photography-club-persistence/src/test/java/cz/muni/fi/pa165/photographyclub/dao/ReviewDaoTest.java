package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.entity.Review;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import java.time.LocalDate;
import java.time.Month;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * @author Pavel Brousek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ReviewDaoTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private TourDao tourDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @Autowired
    private ReviewDao reviewDao;
    
    private static final String DEFAULT_COMMENT = "1";
    
    private Member createFullMember(){
        Member author = new Member();
        author.setName("Karel");
        author.setBirthDate(LocalDate.of(2017, 10, 28));
        memberDao.create(author);
        return author;
    }
    private Tour createFullTour(){
        Tour tour = new Tour();
        tour.setName("Landscape 2017");
        tour.setTheme(TourTheme.LANDSCAPE);
        tour.setDate(LocalDate.of(2017, Month.NOVEMBER, 26));
        tourDao.create(tour);
        return tour;
    }
    
    private Review createFullReview() {
        Member author = createFullMember();
        Tour tour = createFullTour();
        Review review = new Review();
        review.setAuthor(author);
        review.setTour(tour);
        review.setComment(DEFAULT_COMMENT);
        reviewDao.create(review);
        return review;
    }
    
    @Test()
    public void savesComment(){
        Review review = createFullReview();
        
        assertThat(review.getComment()).isEqualTo(DEFAULT_COMMENT);
    }
    
    @Test()
    public void delete(){
        Review review = createFullReview();
        assertThat(reviewDao.findById(review.getId())).isNotNull();
        reviewDao.remove(review);
        assertThat(reviewDao.findById(review.getId())).isNull();
    }
}
