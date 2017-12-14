package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.EquipmentCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.entity.Equipment;
import cz.muni.fi.pa165.photographyclub.facade.EntityNotFoundException;
import cz.muni.fi.pa165.photographyclub.facade.EquipmentFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.InvalidParameterException;
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
     * Get list of equipment associated to a member
     *
     * @param memberId id of the member
     * @return list of equipment
     * @throws Exception ResourceNotFoundException if there is empty result
     */
    @RequestMapping(value = "members/{member_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<EquipmentDTO> getEquipmentByMember(@PathVariable("member_id") long memberId) throws Exception{
        try{
            return equipmentFacade.getEquipmentByMember(memberId);
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Adds equipment to a member
     *
     * @param memberId id of the member
     * @param equipmentCreateDTO equipment to be added to the member
     */
    @RequestMapping(value = "/members/{member_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void addEquipmentToMember(@PathVariable("member_id") long memberId, @RequestBody EquipmentCreateDTO equipmentCreateDTO){
        try {
            equipmentFacade.addEquipmentToMember(memberId, equipmentCreateDTO);
        } catch (IllegalArgumentException e){
            throw new InvalidParameterException();
        }
    }

    /**
     * Remove equipment from a member
     *
     * @param memberId id of the member
     * @param equipmentId equipment to be removed from the member
     */
    @RequestMapping(value = "/{equipment_id}/members/{member_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeEquipmentFromMember(@PathVariable("member_id") long memberId, @PathVariable("equipment_id") long equipmentId){
        try {
            equipmentFacade.removeEquipmentOfMember(memberId, equipmentId);
        } catch (EntityNotFoundException e){
            if (e.getEntityClass().equals(Equipment.class)) {
                throw new ResourceNotFoundException();
            } else {
                throw new InvalidParameterException();
            }
        }
    }

}