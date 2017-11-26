package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.ServiceTestApplicationContext;
import cz.muni.fi.pa165.photographyclub.dao.TourDao;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
@ContextConfiguration(classes = ServiceTestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TourServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private TourDao dao;
    
    @InjectMocks
    private TourServiceImpl service;
    
    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void getAllToursEmptyTest(){
        assertThat(service.findAll()).isEmpty();
        verify(dao, times(1)).findAll();
    }
    
}
