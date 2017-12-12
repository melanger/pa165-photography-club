package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.facade.EquipmentFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

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
}