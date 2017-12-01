package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.ServiceTestApplicationContext;
import cz.muni.fi.pa165.photographyclub.dao.EquipmentDao;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**Tests for Equipment service
 * @author Denis.Figula
 */
@ContextConfiguration(classes = ServiceTestApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TheEquipmentServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private EquipmentDao equipmentDao;

    @InjectMocks
    private EquipmentServiceImpl equipmentService;

    @BeforeClass
    public void setup() {
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

    @Test
    public void createTest(){
        equipmentService.create(testEquipment);
        verify(equipmentDao, times(1)).create(same(testEquipment));
    }

    @Test
    public void findAll(){
        Equipment testEquipment2 = new Equipment();
        testEquipment2.setName("TestE2");
        List<Equipment> equipmentList = new LinkedList<>();
        equipmentList.add(testEquipment);
        equipmentList.add(testEquipment2);
        when(equipmentDao.findAll()).thenReturn(equipmentList);
        assertThat(equipmentService.findAll()).isSameAs(equipmentList);
    }

    @Test
    public void remove(){
        equipmentService.create(testEquipment);
        equipmentService.remove(testEquipment);
        verify(equipmentDao, times(1)).remove(same(testEquipment));
    }

    @Test
    public void findById(){
        equipmentService.create(testEquipment);
        when(equipmentDao.findById(testEquipment.getId())).thenReturn(testEquipment);
        assertThat(equipmentService.findById(testEquipment.getId())).isEqualToComparingFieldByField(testEquipment);
    }

    @Test
    public void findByOwner(){
        Member member = new Member();
        member.setName("Anton");
        testEquipment.setOwner(member);
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(testEquipment);
        when(equipmentDao.findByOwner(member)).thenReturn(equipmentList);
    }

    @Test
    public void getEquipmentInventoryByType(){
        equipmentService.create(testEquipment);
        List<Equipment> cameraList = new ArrayList<>();
        cameraList.add(testEquipment);
        Map<EquipmentType,List<Equipment>> inventory = new HashMap<>();
        assertThat(equipmentService.getEquipmentInventoryByType()).containsEntry(EquipmentType.CAMERA,cameraList);
    }
}
