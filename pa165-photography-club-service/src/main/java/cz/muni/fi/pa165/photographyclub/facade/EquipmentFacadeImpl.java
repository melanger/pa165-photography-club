package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.beanmapping.BeanMappingService;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.entity.Member;
import cz.muni.fi.pa165.photographyclub.service.EquipmentService;
import cz.muni.fi.pa165.photographyclub.service.MemberService;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Implementation of EquipmentFacade.
 * @author Matus Kravec.
 */
@Transactional
@Service
public class EquipmentFacadeImpl implements EquipmentFacade{

    @Inject
    private EquipmentService equipmentService;
    
    @Inject
    private MemberService memberService;
    
    @Inject
    private BeanMappingService beanMappingService;
    
    @Override
    public List<EquipmentDTO> getEquipmentByMember(long memberId) {
        Member member = memberService.findById(memberId);
        if (member == null) throw new EntityNotFoundException();
        List<Equipment> equipList = equipmentService.findByOwner(member);
        return beanMappingService.mapTo(equipList, EquipmentDTO.class);
    }

    @Override
    public EquipmentDTO getEquipmentById(long id) {
        Equipment equipment = equipmentService.findById(id);
        return equipment == null ? null : beanMappingService.mapTo(equipment, EquipmentDTO.class);
    }

    @Override
    public void createEquipment(EquipmentCreateDTO equipment) {
        Member member = memberService.findById(equipment.getOwnerId());
        if (member == null) throw new PhotoEntityNotFoundException(Member.class);
        List<Equipment> equipList = member.getEquipment();
        Equipment newEquipment = new Equipment();
        newEquipment.setName(equipment.getName());
        newEquipment.setType(equipment.getType());
        equipmentService.create(newEquipment);
        newEquipment.setOwner(member);
        equipList.add(newEquipment);
        member.setEquipment(equipList);
    }

    @Override
    public void removeEquipment(long equipmentId) {
        Equipment equipment = equipmentService.findById(equipmentId);
        if (equipment == null) throw new EntityNotFoundException();
        
        Member member = equipment.getOwner();
        List<Equipment> equipList = member.getEquipment();
        
        equipList.remove(equipment);
        member.setEquipment(equipList);
        equipmentService.remove(equipment);
    }

}
