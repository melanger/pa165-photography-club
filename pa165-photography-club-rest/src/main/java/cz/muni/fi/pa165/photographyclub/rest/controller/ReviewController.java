package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.ReviewCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * REST Controller for Review.
 * @author Matus Kravec.
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_REVIEWS)
public class ReviewController {
    @Inject
    private ReviewFacade reviewFacade;
    
    /**   
     * Method for getting review specified by Id parameter.
     * @param id of the review
     * @return found review.
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ReviewDTO getReviewById(@PathVariable("id") long id) throws Exception {
        ReviewDTO reviewDTO = reviewFacade.getReviewById(id);
        if (reviewDTO == null) {
            throw new ResourceNotFoundException();
        }
        return reviewDTO;
    }
    
    /**
     * Method for creating new review.
     * @param reviewCreateDTO review to be created.
     * @throws Exception {@link ResourceAlreadyExistingException}
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> createReview(@RequestBody ReviewCreateDTO reviewCreateDTO, UriComponentsBuilder builder) throws Exception{
        long id;
        try {
            id = reviewFacade.createReview(reviewCreateDTO);
        } catch (EntityExistsException e) {
            throw new ResourceAlreadyExistingException();
        }
        
        UriComponents uriComponents = builder.path(ApiUris.ROOT_URI_REVIEWS + "/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
    
    /**
     * Method for deleting review.
     * @param reviewrId Id parameter of review.
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/{review_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeReview(@PathVariable("review_id") long reviewrId) throws Exception{
        try {
            reviewFacade.removeReview(reviewrId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }
    
    /**
     * Method for getting all Reviews.
     * @return List of all reviews.
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ReviewDTO> getAllReviews(){
        return reviewFacade.getAllReviews();
    }
    
    /**
     * Method for updating review.
     * @param reviewDTO review to be updated.
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> updateReview(@RequestBody ReviewDTO reviewDTO, UriComponentsBuilder builder) throws Exception{
        long id = reviewDTO.getId();
        reviewFacade.updateReview(reviewDTO);
        
        UriComponents uriComponents = builder.path(ApiUris.ROOT_URI_REVIEWS + "/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
