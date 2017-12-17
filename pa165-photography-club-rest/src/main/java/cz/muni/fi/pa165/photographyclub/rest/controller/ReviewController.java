package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.facade.ReviewFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * @throws Exception ResourceNotFoundException.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ReviewDTO getReview(@PathVariable("id") long id) throws Exception {
        ReviewDTO reviewDTO = reviewFacade.getReviewById(id);
        if (reviewDTO == null) {
            throw new ResourceNotFoundException();
        }
        return reviewDTO;
    }
}
