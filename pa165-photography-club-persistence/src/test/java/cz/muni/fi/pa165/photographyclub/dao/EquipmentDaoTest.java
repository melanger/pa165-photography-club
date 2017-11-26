package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
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
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Kravec.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class EquipmentDaoTest extends AbstractTestNGSpringContextTests {
        
    @Autowired
    private EquipmentDao equipmentDao;
    
    @Autowired
    private MemberDao memberDao;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    private Equipment makeEquipment(){
        Equipment equipment = new Equipment();
        equipment.setName("Nikon D5");
        Member owner = new Member();
        owner.setName("John Smith");
        owner.setBirthDate(LocalDate.of(1990, Month.NOVEMBER, 11));
        equipment.setOwner(owner);
        equipment.setType(EquipmentType.CAMERA);
        memberDao.create(owner);
        equipmentDao.create(equipment);
        return equipment;
    }
        
    @Test
    public void createEquipmentTest(){
        Equipment equipment = makeEquipment();
        
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Equipment equipmentTmp = null;
        equipmentTmp = entityManager.find(Equipment.class, equipment.getId());
        Assert.assertNotNull(equipmentTmp);
        Assert.assertEquals(equipment.getName(), "Nikon D5");
        Assert.assertEquals(equipment.getOwner().getName(), "John Smith");
        entityManager.close();        
    }
    
    @Test
    public void removeEquipmentTest(){
        Equipment equipment = makeEquipment();
        equipmentDao.remove(equipment);
        
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Equipment equipmentTmp = null;
        equipmentTmp = entityManager.find(Equipment.class, equipment.getId());
        Assert.assertNull(equipmentTmp);
    }
    
    @Test
    public void findEquipmentByIdTest(){
        Equipment equipment = makeEquipment();
        
        Equipment equipmentTmp = equipmentDao.findById(equipment.getId());
        Assert.assertNotNull(equipmentTmp);
        Assert.assertEquals(equipmentTmp, equipment);       
    }
    
    @Test
    public void findEquipmentByOwnerTest(){
        Equipment equipment = makeEquipment();
        
        List<Equipment> euqipList = equipmentDao.findByOwner(equipment.getOwner());
        Assert.assertNotNull(euqipList);
        Assert.assertNotEquals(euqipList.size(), 0);
    }    
    
    @Test
    public void findAllEquipmentTest(){
        makeEquipment();
        
        List<Equipment> euqipList = equipmentDao.findAll();
        Assert.assertNotNull(euqipList);
        Assert.assertNotEquals(euqipList.size(), 0);
    }
        
}
