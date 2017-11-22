package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.dao.EquipmentDao;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class EquipmentServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private EquipmentDao equipmentDao;

    @Autowired
    @InjectMocks
    private EquipmentService productService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }

    private Equipment testEquipment;

    @BeforeMethod
    public void prepareEquipment(){
        testEquipment = new Equipment();
        testEquipment.setName("TestE");
        testEquipment.setOwner(new Member());
        testEquipment.setType(EquipmentType.CAMERA);
    }
}
