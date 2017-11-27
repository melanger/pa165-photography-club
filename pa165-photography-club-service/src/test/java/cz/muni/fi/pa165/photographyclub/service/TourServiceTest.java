package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.ServiceTestApplicationContext;
import cz.muni.fi.pa165.photographyclub.dao.TourDao;
import cz.muni.fi.pa165.photographyclub.entity.Tour;
import cz.muni.fi.pa165.photographyclub.enums.TourTheme;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.same;
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
@ContextConfiguration(classes = ServiceTestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TourServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private TourDao tourDao;
    
    @InjectMocks
    private TourServiceImpl service;
    
    private Tour t1;
    
    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeMethod
    public void setupMethod(){
        t1 = new Tour();
        t1.setName("Test 1");
        t1.setDate(LocalDate.of(2017, Month.NOVEMBER, 26));
        t1.setTheme(TourTheme.PORTRAITS);
        t1.setParticipants(new LinkedList());
        t1.setReviews(new LinkedList());
    }
    
    @Test
    public void findAllTest(){
        Tour t2 = new Tour();
        t2.setName("Test 2");
        List<Tour> tList = new LinkedList<>();
        tList.add(t1);
        tList.add(t2);
        
        when(tourDao.findAll()).thenReturn(tList);
        assertThat(service.findAll()).isSameAs(tList);
    }
    
    @Test
    public void createTest(){
        service.create(t1);
        verify(tourDao, times(1)).create(same(t1));
    }
    
    @Test
    public void findByIdTest(){
        final long id = 42l;
        t1.setId(id);
        when(tourDao.findById(t1.getId())).thenReturn(t1);
        
        assertThat(service.findById(id)).isEqualToComparingFieldByField(t1);
    }
    
    @Test
    public void removeTest(){
        final long id = 42l;
        t1.setId(id);
        
        service.remove(t1);
        verify(tourDao, times(1)).remove(same(t1));
    }
}
