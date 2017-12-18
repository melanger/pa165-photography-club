package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 *
 * @author Matus Kravec.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TourDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private TourDao tourDao;

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Tour makeTour() {
        Tour tour = new Tour();
        tour.setName("Test");
        tour.setTheme(TourTheme.PORTRAITS);
        tour.setDate(LocalDate.of(2017, Month.NOVEMBER, 26));
        tourDao.create(tour);
        return tour;
    }

    @Test
    public void createTourTest() {
        Tour tour = makeTour();

        Tour tourTmp = null;
        tourTmp = tourDao.findById(tour.getId());
        assertThat(tourTmp).isNotNull();
        assertThat(tour.getName()).isEqualTo("Test");
    }

    @Test
    public void removeTourTest() {
        Tour tour = makeTour();
        tourDao.remove(tour);

        Tour tourTmp = null;
        tourTmp = tourDao.findById(tour.getId());
        assertThat(tourTmp).isNull();

    }

    @Test
    public void updateTourTest() {
        Tour tour = makeTour();
        tour.setName("TestUpdate");
        tourDao.update(tour);

        Tour tourTmp = null;
        tourTmp = tourDao.findById(tour.getId());
        assertThat(tourTmp).isNotNull();
        assertThat(tour.getName()).isEqualTo("TestUpdate");
    }

    @Test
    public void getAllToursTest() {
        Tour tour2 = new Tour();
        tour2.setName("Test2");
        tour2.setTheme(TourTheme.PORTRAITS);
        tour2.setDate(LocalDate.of(2017, Month.NOVEMBER, 26));
        Tour tour3 = new Tour();
        tour3.setName("Test3");
        tour3.setTheme(TourTheme.PORTRAITS);
        tour3.setDate(LocalDate.of(2017, Month.NOVEMBER, 26));
        tourDao.create(tour2);
        tourDao.create(tour3);

        List<Tour> tourList = tourDao.findAll();
        assertThat(tourList).isNotNull();
        assertThat(tourList.size()).isSameAs(2);
    }

    @Test
    public void getTourByIdTest() {
        Tour tour = makeTour();

        Tour tourTmp = tourDao.findById(tour.getId());
        assertThat(tourTmp).isNotNull();
        assertThat(tourTmp).isEqualTo(tour);
    }

    @Test
    public void getTourByNameTest() {
        Tour tour = new Tour();
        tour.setName("NameTest");
        tour.setTheme(TourTheme.PORTRAITS);
        tour.setDate(LocalDate.of(2017, Month.NOVEMBER, 26));
        tourDao.create(tour);

        Tour tourTmp = tourDao.findByName(tour.getName());
        assertThat(tourTmp).isNotNull();
        assertThat(tourTmp).isEqualTo(tour);
    }

    @Test
    public void getTourByIdNullTest() {
        assertThat(tourDao.findById(0l)).isNull();
    }

    @Test
    public void getTourByNameNullTest() {
        assertThat(tourDao.findByName("Null")).isNull();
    }

    @Test
    public void getAllToursEmptyTest() {
        assertThat(tourDao.findAll()).isEmpty();
    }
    
    @Test
    public void deleteNonExistingTourTest() {
        Tour t = new Tour();
        t.setId(7777l);
        try {
            tourDao.remove(t);
            failBecauseExceptionWasNotThrown(DataAccessException.class);
        } catch (DataAccessException e) {
            //
        }
    }
}
