package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.EquipmentCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import java.util.List;

/**
 * Facade interface for functions related to the Equipment entity.
 * @author Pavel Brousek
 */
public interface EquipmentFacade {
    
    public List<EquipmentDTO> getEquipmentByMember(long memberId);
    
    public EquipmentDTO getEquipmentById(long id);
    
    public void addEquipmentToMember(long memberId, EquipmentCreateDTO equipment);
    
    public void removeEquipmentOfMember(long memberId, long equipmentId);
    
}
