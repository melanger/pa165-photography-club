package cz.muni.fi.pa165.photographyclub.dao;

import cz.muni.fi.pa165.photographyclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 *
 * @author Matus Kravec.
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class EquipmentDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private MemberDao memberDao;

    private Equipment makeEquipment() {
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
    public void createEquipmentTest() {
        Equipment equipment = makeEquipment();

        Equipment equipmentTmp = null;
        equipmentTmp = equipmentDao.findById(equipment.getId());
        assertThat(equipmentTmp).isNotNull();
        assertThat(equipment.getName()).isEqualTo("Nikon D5");
        assertThat(equipment.getOwner().getName()).isEqualTo("John Smith");
    }

    @Test
    public void removeEquipmentTest() {
        Equipment equipment = makeEquipment();
        equipmentDao.remove(equipment);

        Equipment equipmentTmp = null;
        equipmentTmp = equipmentDao.findById(equipment.getId());
        assertThat(equipmentTmp).isNull();
    }

    @Test
    public void findEquipmentByIdTest() {
        Equipment equipment = makeEquipment();

        Equipment equipmentTmp = equipmentDao.findById(equipment.getId());
        assertThat(equipmentTmp).isNotNull();
        assertThat(equipmentTmp).isEqualTo(equipment);
    }

    @Test
    public void findEquipmentByOwnerTest() {
        Equipment equipment = makeEquipment();

        List<Equipment> euqipList = equipmentDao.findByOwner(equipment.getOwner());
        assertThat(euqipList).isNotNull();
        assertThat(euqipList.size()).isNotSameAs(0);
    }

    @Test
    public void findAllEquipmentTest() {
        makeEquipment();

        List<Equipment> euqipList = equipmentDao.findAll();
        assertThat(euqipList).isNotNull();
        assertThat(euqipList.size()).isNotSameAs(0);
    }

}
