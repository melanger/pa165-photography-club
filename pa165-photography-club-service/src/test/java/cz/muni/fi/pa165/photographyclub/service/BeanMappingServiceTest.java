package cz.muni.fi.pa165.photographyclub.service;

import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.beanmapping.config.BeanMappingApplicationContext;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = BeanMappingApplicationContext.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private BeanMappingService beanMappingService;
    
    private final List<Equipment> equipment = new ArrayList<>();
    private Equipment e1;
    private Equipment e2;
    
    @BeforeMethod
    public void createEquipment(){
	e1 = new Equipment();
        e1.setName("Test 1");
        e2 = new Equipment();
        e2.setName("Test 2");
        
        equipment.add(e1);
        equipment.add(e2);
    }
    
    @Test
    public void shouldMapEquipmentLists(){
    	List<EquipmentDTO> equipmentDTOs = beanMappingService.mapTo(equipment, EquipmentDTO.class);
    	assertThat(equipmentDTOs).hasSize(equipment.size());
    	assertThat(equipmentDTOs.get(0)).hasFieldOrPropertyWithValue("name", e1.getName());
        assertThat(equipmentDTOs.get(1)).hasFieldOrPropertyWithValue("name", e2.getName());
    }
    
}
