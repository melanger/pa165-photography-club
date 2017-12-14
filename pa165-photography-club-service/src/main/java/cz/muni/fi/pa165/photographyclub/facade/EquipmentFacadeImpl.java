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
        if (member == null){
            return null;
        } else {
            List<Equipment> equipList = equipmentService.findByOwner(member);
            return beanMappingService.mapTo(equipList, EquipmentDTO.class);
        }
    }

    @Override
    public EquipmentDTO getEquipmentById(long id) {
        Equipment equipment = equipmentService.findById(id);
        if(equipment == null){
            return null;
        } else {
            return beanMappingService.mapTo(equipment, EquipmentDTO.class);
        }
    }

    @Override
    public void addEquipmentToMember(long memberId, EquipmentCreateDTO equipment) {
        Member member = memberService.findById(memberId);
        if (member == null || equipment.getOwner() == null || equipment.getOwner().getId() != memberId) {
            throw new IllegalArgumentException("Invalid member in arguments");
        }
        List<Equipment> equipList = member.getEquipment();
        Equipment newEquipment = new Equipment();
        newEquipment.setName(equipment.getName());
        newEquipment.setOwner(beanMappingService.mapTo(equipment.getOwner(), Member.class));
        newEquipment.setType(equipment.getType());
        equipList.add(newEquipment);
        member.setEquipment(equipList);
    }

    @Override
    public void removeEquipmentOfMember(long memberId, long equipmentId) {
        Member member = memberService.findById(memberId);
        if (member == null) throw new EntityNotFoundException(Member.class);
        List<Equipment> equipList = member.getEquipment();
        Equipment equipment = equipmentService.findById(equipmentId);
        if (equipment == null) throw new EntityNotFoundException(Equipment.class);
        equipList.remove(equipment);
        member.setEquipment(equipList);
    }

}
