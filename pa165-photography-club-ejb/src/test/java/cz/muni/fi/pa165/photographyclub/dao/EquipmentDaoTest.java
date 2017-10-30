package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import cz.muni.fi.pa165.photographyclub.service.ServiceImpl;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Kravec.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class EquipmentDaoTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private ServiceImpl service;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    private Equipment makeEquipment(){
        Equipment equipment = new Equipment();
        equipment.setName("Nikon D5");
        Member owner = new Member();
        owner.setName("John Smith");
        owner.setBirthDate(Date.from(Instant.now()));
        equipment.setOwner(owner);
        equipment.setType(EquipmentType.CAMERA);
        service.createMember(owner);
        service.createEquipment(equipment);
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
        service.removeEquipment(equipment);
        
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Equipment equipmentTmp = null;
        equipmentTmp = entityManager.find(Equipment.class, equipment.getId());
        Assert.assertNull(equipmentTmp);
    }
    
    @Test
    public void findEquipmentByIdTest(){
        Equipment equipment = makeEquipment();
        
        Equipment equipmentTmp = service.findEquipmentById(equipment.getId());
        Assert.assertNotNull(equipmentTmp);
        Assert.assertEquals(equipmentTmp, equipment);       
    }
    
    @Test
    public void findEquipmentByOwnerTest(){
        Equipment equipment = makeEquipment();
        
        List<Equipment> euqipList = service.findEquipmentByOwner(equipment.getOwner());
        Assert.assertNotNull(euqipList);
        Assert.assertNotEquals(euqipList.size(), 0);
    }    
    
    @Test
    public void findAllEquipmentTest(){
        Equipment equipment = makeEquipment();
        
        List<Equipment> euqipList = service.findAllEquipment();
        Assert.assertNotNull(euqipList);
        Assert.assertNotEquals(euqipList.size(), 0);
    }
        
}
