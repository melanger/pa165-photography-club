package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.EquipmentCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.facade.EquipmentFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.InvalidParameterException;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

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
     * Adds equipment to a member
     *
     * @param equipmentCreateDTO equipment to be added to the member
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> createEquipment(@RequestBody EquipmentCreateDTO equipmentCreateDTO, UriComponentsBuilder b) throws Exception{
        long id;
        try {
            id = equipmentFacade.createEquipment(equipmentCreateDTO);
        } catch (EntityNotFoundException e){
            throw new InvalidParameterException();
        }
        UriComponents uriComponents = b.path(ApiUris.ROOT_URI_EQUIPMENT + "/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    /**
     * Remove equipment from a member
     *
     * @param equipmentId equipment to be removed from the member
     */
    @RequestMapping(value = "/{equipment_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeEquipment(@PathVariable("equipment_id") long equipmentId){
        try {
            equipmentFacade.removeEquipment(equipmentId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }

}