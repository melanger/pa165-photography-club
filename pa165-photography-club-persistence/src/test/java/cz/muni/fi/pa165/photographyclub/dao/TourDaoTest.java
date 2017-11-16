package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import cz.muni.fi.pa165.photographyclub.service.ServiceImpl;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Matus Kravec.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext
public class TourDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private ServiceImpl service;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    private Tour makeTour(){
        Tour tour = new Tour();
        tour.setName("Test");
        tour.setTheme(TourTheme.PORTRAITS);
        tour.setDate(Date.from(Instant.now()));
        service.createTour(tour);
        return tour;
    }
        
    @Test
    public void createTourTest() {
        Tour tour = makeTour();
        
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Tour tourTmp = null;
        tourTmp = entityManager.find(Tour.class, tour.getId());
        Assert.assertNotNull(tourTmp);
        Assert.assertEquals(tour.getName(), "Test");
        entityManager.close();           
    }
    
    @Test
    public void removeTourTest() {
        Tour tour = makeTour();
        service.removeTour(tour);
        
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Tour tourTmp = null;
        tourTmp = entityManager.find(Tour.class, tour.getId());
        Assert.assertNull(tourTmp);
        
    }
    
    @Test
    public void updateTourTest() {
        Tour tour = makeTour();
        tour.setName("TestUpdate");
        service.updateTour(tour);
        
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Tour tourTmp = null;
        tourTmp = entityManager.find(Tour.class, tour.getId());
        Assert.assertNotNull(tourTmp);
        Assert.assertEquals(tour.getName(), "TestUpdate");        
    }
    
    @Test
    public void getAllToursTest() {
        Tour tour2 = new Tour();
        tour2.setName("Test2");
        tour2.setTheme(TourTheme.PORTRAITS);
        tour2.setDate(Date.from(Instant.now()));
        Tour tour3 = new Tour();
        tour3.setName("Test3");
        tour3.setTheme(TourTheme.PORTRAITS);
        tour3.setDate(Date.from(Instant.now()));
        service.createTour(tour2);
        service.createTour(tour3);
        
        List<Tour> tourList = service.getAllTours();
        Assert.assertNotNull(tourList);
        Assert.assertEquals(tourList.size(), 3);       
    }
    
    @Test
    public void getTourByIdTest() {
        Tour tour = makeTour();
        
        Tour tourTmp = service.getTourByID(tour.getId());
        Assert.assertNotNull(tourTmp);
        Assert.assertEquals(tourTmp, tour);       
    }
    
    @Test
    public void getTourByNameTest() {
        Tour tour = new Tour();
        tour.setName("NameTest");
        tour.setTheme(TourTheme.PORTRAITS);
        tour.setDate(Date.from(Instant.now()));
        service.createTour(tour);
        
        Tour tourTmp = service.getTourByName(tour.getName());
        Assert.assertNotNull(tourTmp);
        Assert.assertEquals(tourTmp, tour);       
    }

    @Test
    public void getTourByIdNullTest(){
        assertThat(service.getTourByID(0l)).isNull();
    }

    @Test
    public void getTourByNameNullTest(){
        assertThat(service.getTourByName("Null")).isNull();
    }

    @Test
    public void getAllToursEmptyTest(){
        assertThat(service.getAllTours()).isEmpty();
    }
}
