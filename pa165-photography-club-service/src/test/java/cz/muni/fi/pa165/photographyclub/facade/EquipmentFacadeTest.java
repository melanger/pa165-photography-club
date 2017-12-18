package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.facade.config.FacadeApplicationContext;
import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.enums.EquipmentType;
import cz.muni.fi.pa165.photographyclub.service.EquipmentService;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *  Tests for EquipmentFacade.
 * @author Matus Kravec.
 */
@ContextConfiguration(classes = FacadeApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EquipmentFacadeTest extends AbstractTestNGSpringContextTests{

    @Mock
    private EquipmentService equipmentService;
    
    @Mock
    private MemberService memberService;
    
    @Mock
    private BeanMappingService beanMappingService;
    
    @InjectMocks
    private EquipmentFacadeImpl equipmentFacade;
    
    @BeforeClass
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    private Member createMember(){
       Member member = new Member();
       member.setId(42l);
       member.setName("John Smith");
       member.setBirthDate(LocalDate.of(1990, Month.JUNE, 15));  
       member.setEquipment(new LinkedList());
       return member;
    }
    
    private MemberDTO createMemberDTO(){
       MemberDTO member = new MemberDTO();
       member.setId(42l);
       member.setName("John Smith");
       member.setBirthDate(LocalDate.of(1990, Month.JUNE, 15));
       member.setEquipment(new LinkedList());       
       return member;
    }
    
    private Equipment createEquipment(){
        Equipment equipment = new Equipment();
        equipment.setId(420l);
        equipment.setName("Nikon D5000");
        equipment.setOwner(this.createMember());
        equipment.setType(EquipmentType.CAMERA);
        return equipment;
    }
    
    private EquipmentDTO createEquipmentDTO(){
        EquipmentDTO equipment = new EquipmentDTO();
        equipment.setId(420l);
        equipment.setName("Nikon D5000");
        equipment.setOwner(this.createMemberDTO());
        equipment.setType(EquipmentType.CAMERA);
        return equipment;
    }
    
    @Test
    public void getEquipmentByMemberTest(){
        Member member = this.createMember();
        List<Equipment> equipList = new LinkedList();
        Equipment equipment = this.createEquipment();
        equipList.add(equipment);
        member.setEquipment(equipList);
        List<EquipmentDTO> equipDTOList = new LinkedList();
        equipDTOList.add(this.createEquipmentDTO());
        
        when(memberService.findById(member.getId())).thenReturn(member);
        when(equipmentService.findByOwner(member)).thenReturn(equipList);
        when(beanMappingService.mapTo(equipList, EquipmentDTO.class)).thenReturn(equipDTOList);
        
        Assert.assertEquals(equipmentFacade.getEquipmentByMember(member.getId()), equipDTOList);
    }
    
    @Test(expectedExceptions = EntityNotFoundException.class)
    public void getEquipmentByNonExistingMemberIdTest() throws EntityNotFoundException {
        long nonExistingId = 7777;
        equipmentFacade.getEquipmentByMember(nonExistingId);
    }
    
    @Test
    public void getEquipmentByIdTest(){
        Equipment equipment = this.createEquipment();
        EquipmentDTO equipmentDTO = this.createEquipmentDTO();
        
        when(equipmentService.findById(equipment.getId())).thenReturn(equipment);
        when(beanMappingService.mapTo(equipment, EquipmentDTO.class)).thenReturn(equipmentDTO);
        
        Assert.assertEquals(equipmentFacade.getEquipmentById(equipment.getId()), equipmentDTO);
    }
    
    @Test
    public void addEquipmentToMemberTest(){
        Member member = this.createMember();
        MemberDTO memberDTO = this.createMemberDTO();
        EquipmentCreateDTO equipmentCreate = new EquipmentCreateDTO();
        equipmentCreate.setName("Nikon D5000");
        equipmentCreate.setOwnerId(member.getId());
        equipmentCreate.setType(EquipmentType.CAMERA);
        List<Equipment> equipList = new LinkedList();
        Equipment equipment = this.createEquipment();
        equipList.add(equipment);
        Member mockMember = mock(Member.class);
        
        when(memberService.findById(member.getId())).thenReturn(mockMember);
        when(beanMappingService.mapTo(memberDTO, Member.class)).thenReturn(member);
        
        equipmentFacade.createEquipment(equipmentCreate);
        
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mockMember).setEquipment(argumentCaptor.capture());   
        List arg = argumentCaptor.getValue();
        Assert.assertEquals(arg.size(), 1);
    }
         
    @Test
    public void removeEquipmentOfMemberTest(){
        Member member = this.createMember();
        List<Equipment> equipList = new LinkedList();
        Equipment equipment = this.createEquipment();
        equipList.add(equipment);
        member.setEquipment(equipList);
        Member mockMember = mock(Member.class);
        
        when(memberService.findById(member.getId())).thenReturn(mockMember);
        when(equipmentService.findById(equipment.getId())).thenReturn(equipment);
        when(mockMember.getEquipment()).thenReturn(equipList);
        
        equipmentFacade.removeEquipment(equipment.getId());
        
        verify(equipmentService).remove(equipment);       
    }
}
