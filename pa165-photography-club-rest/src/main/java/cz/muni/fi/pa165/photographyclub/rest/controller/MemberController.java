package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.facade.MemberFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Member.
 * @author Matus Kravec.
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_MEMBERS)
public class MemberController {

    @Inject
    private MemberFacade memberFacade;
    
    /**
     * Method for getting member specified by Id parameter.
     * @param id of the member
     * @return found member.
     * @throws Exception ResourceNotFoundException.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final MemberDTO getMember(@PathVariable("id") long id) throws Exception {
        MemberDTO memberDTO = memberFacade.findById(id);
        if (memberDTO == null) {
            throw new ResourceNotFoundException();
        }
        return memberDTO;
    }
}
