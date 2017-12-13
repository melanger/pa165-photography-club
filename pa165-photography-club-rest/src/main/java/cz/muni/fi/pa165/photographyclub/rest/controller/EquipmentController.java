package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.EquipmentCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.facade.EquipmentFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import javax.inject.Inject;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * REST Controller for Equipment
 * @author Pavel Brousek
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_EQUIPMENT)
public class EquipmentController {
    @Inject
    private EquipmentFacade equipmentFacade;

    /**
     * 
     * Get one equipment specified by id
     * 
     * @param id identifier for the equipment
     * @return EquipmentDTO
     * @throws Exception ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final EquipmentDTO getEquipment(@PathVariable("id") long id) throws Exception {
        EquipmentDTO equipmentDTO = equipmentFacade.getEquipmentById(id);
        if (equipmentDTO == null) {
            throw new ResourceNotFoundException();
        }

        return equipmentDTO;
    }

    /**
     * Get list of equipment associated to member
     *
     * @param memberId id of member
     * @return list of equipment
     * @throws Exception ResourceNotFoundException if there is empty result
     */
    @RequestMapping(value = "by_member_id/{member_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<EquipmentDTO> getEquipmentByMember(@PathVariable("member_id") long memberId) throws Exception{
        List<EquipmentDTO> equipmentDTOList = equipmentFacade.getEquipmentByMember(memberId);
        if (equipmentDTOList == null) {
            throw new ResourceNotFoundException();
        }
        return equipmentDTOList;
    }

    /**
     * Adds equipment to member
     *
     * @param memberId id of member
     * @param equipmentCreateDTO equipment to be added to member
     */
    @RequestMapping(value = "/{member_id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void addEquipmentToMember(@PathVariable("member_id") long memberId, @RequestBody EquipmentCreateDTO equipmentCreateDTO){
        equipmentFacade.addEquipmentToMember(memberId,equipmentCreateDTO);
    }

    /**
     * Remove equipment from member
     *
     * @param memberId id of member
     * @param equipmentId equipment to be removed from member
     */
    @RequestMapping(value = "/{member_id},{equipment_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeEquipmentFromMember(@PathVariable("member_id") long memberId, @PathVariable("equipment_id") long equipmentId){
        equipmentFacade.removeEquipmentOfMember(memberId,equipmentId);
    }

}