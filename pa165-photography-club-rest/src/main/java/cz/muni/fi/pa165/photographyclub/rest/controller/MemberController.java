package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.EquipmentDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.facade.EquipmentFacade;
import cz.muni.fi.pa165.photographyclub.facade.MemberFacade;
import cz.muni.fi.pa165.photographyclub.facade.ReviewFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * REST Controller for Member.
 * @author Matus Kravec.
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_MEMBERS)
public class MemberController {

    @Inject
    private MemberFacade memberFacade;
    
    @Inject
    private EquipmentFacade equipmentFacade;
    
    @Inject
    private ReviewFacade reviewFacade;
    
    /**
     * Method for getting member specified by Id parameter.
     * @param id of the member
     * @return found member.
     * @throws Exception ResourceNotFoundException.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MemberDTO getMemberById(@PathVariable("id") long id) throws Exception {
        MemberDTO memberDTO = memberFacade.findById(id);
        if (memberDTO == null) {
            throw new ResourceNotFoundException();
        }
        return memberDTO;
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MemberDTO> getAllMembers(){
        return memberFacade.findAllMembers();
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MemberDTO findMemberByName(@RequestParam("name") String name) throws Exception {
        MemberDTO memberDTO = memberFacade.findByName(name);
        if (memberDTO == null) {
            throw new ResourceNotFoundException();
        }
        return memberDTO;
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> createMember(@RequestBody MemberCreateDTO memberCreateDTO, UriComponentsBuilder builder) throws Exception{
        long id;
        try {
            id = memberFacade.createMember(memberCreateDTO);
        } catch (EntityExistsException e) {
            throw new ResourceAlreadyExistingException();
        }
        
        UriComponents uriComponents = builder.path(ApiUris.ROOT_URI_MEMBERS + "/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
    
    @RequestMapping(value = "/{member_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeMember(@PathVariable("member_id") long memberId) throws Exception{
        try {
            memberFacade.removeMember(memberId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * Get list of equipment associated to a member
     *
     * @param memberId id of the member
     * @return list of equipment
     * @throws Exception ResourceNotFoundException if the member is not found
     */
    @RequestMapping(value = "/{member_id}/equipment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<EquipmentDTO> getEquipmentByMember(@PathVariable("member_id") long memberId) throws Exception{
        try{
            return equipmentFacade.getEquipmentByMember(memberId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }
    
    @RequestMapping(value = "/{authorId}/reviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ReviewDTO> getReviewsByAuthor(@PathVariable("authorId") Long authorId) throws Exception {
        try {
            return reviewFacade.getReviewsByAuthor(authorId);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }
    
}
