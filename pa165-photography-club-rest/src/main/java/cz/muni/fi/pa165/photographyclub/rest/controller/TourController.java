package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourDTO;
import cz.muni.fi.pa165.photographyclub.facade.TourFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * REST controller for equipment
 * @author Denis.Figula
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_TOURS)
public class TourController {

    @Inject
    private TourFacade tourFacade;

    /**
     * Get all tours available
     * @return list of all tours
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TourDTO> getTours(){
        return tourFacade.getAllTours();
    }

    /**
     * Getting tour by id
     * @param tourId id of the tour
     * @return tour
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/{tour_id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TourDTO getTourById(@PathVariable("tour_id") long tourId) throws Exception{
        TourDTO tour = tourFacade.getTourById(tourId);
        if (tour == null) throw new ResourceNotFoundException();
        return tour;
    }

    /**
     * Creating new tour
     * @param tourCreateDTO new tour
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ResponseEntity<?> createTour(@RequestBody TourCreateDTO tourCreateDTO, UriComponentsBuilder b) throws Exception{
        long id;
        try {
            id = tourFacade.createTour(tourCreateDTO);
        } catch (EntityExistsException e) {
            throw new ResourceAlreadyExistingException();
        }
        
        UriComponents uriComponents = b.path(ApiUris.ROOT_URI_TOURS + "/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    /**
     * Deleting tour
     * @param tourId id of tour
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/{tour_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTour(@PathVariable("tour_id") long tourId) throws Exception{
        try {
            tourFacade.removeTour(tourId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Getting all reviews associated with tour
     * @param tourId id of tour
     * @return list of tour's reviews
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/{tour_id}/reviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ReviewDTO> getTourReviews(@PathVariable("tour_id") long tourId) throws Exception{
        try {
            return tourFacade.getTourReviews(tourId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Getting all participants of the tour
     * @param tourId id of the tour
     * @return List of assosiated members
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/{tour_id}/members", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MemberDTO> getTourParticipants(@PathVariable("tour_id") long tourId) throws Exception{
        try {
            return tourFacade.getTourParticipants(tourId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Getting avg. rating for tour
     * @param tourId id of the tour
     * @return value of avg. rating
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/{tour_id}/rating", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final double getTourRating(@PathVariable("tour_id") long tourId) throws Exception{
        try {
            return tourFacade.getTourRating(tourId);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException();
        }
    }


}
