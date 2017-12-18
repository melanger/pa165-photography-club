package cz.muni.fi.pa165.photographyclub.facade;

import cz.muni.fi.pa165.photographyclub.dto.EquipmentCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import java.util.List;

/**
 * Facade interface for functions related to the Equipment entity.
 * @author Pavel Brousek
 */
public interface EquipmentFacade {
    /**
     * Get all equipment of a member.
     * @param memberId id of the member
     * @return list of equipment (might be empty)
     */
    public List<EquipmentDTO> getEquipmentByMember(long memberId);
    
    /**
     * Get an equipment by its id.
     * @param id id of the equipment
     * @return Equipment if found, null otherwise
     */
    public EquipmentDTO getEquipmentById(long id);
    
    /**
     * Add an equipment to a member.
     * @param equipment EquipmentCreateDTO
     */
    public long createEquipment(EquipmentCreateDTO equipment);
    
    /**
     * Remove an equipment from a member.
     * @param equipmentId EquipmentCreateDTO
     */
    public void removeEquipment(long equipmentId);
    
}
