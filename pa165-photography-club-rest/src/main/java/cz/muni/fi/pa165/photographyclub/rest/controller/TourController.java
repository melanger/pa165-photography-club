package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.dto.MemberDTO;
import cz.muni.fi.pa165.photographyclub.dto.ReviewDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourCreateDTO;
import cz.muni.fi.pa165.photographyclub.dto.TourDTO;
import cz.muni.fi.pa165.photographyclub.facade.TourFacade;
import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import cz.muni.fi.pa165.photographyclub.rest.exception.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

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
        try {
            return tourFacade.getTourById(tourId);
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Creating new tour
     * @param tourCreateDTO new tour
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void createTour(@RequestBody TourCreateDTO tourCreateDTO) throws Exception{
        try {
            tourFacade.createTour(tourCreateDTO);
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
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
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Getting all reviews associated with tour
     * @param tourId id of tour
     * @return list of tour's reviews
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/tourReviews/{tour_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ReviewDTO> getTourReviews(@PathVariable("tour_id") long tourId) throws Exception{
        try {
            return tourFacade.getTourReviews(tourId);
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Getting all participants of the tour
     * @param tourId id of the tour
     * @return List of assosiated members
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/tourParticipants/{tour_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<MemberDTO> getTourParticipants(@PathVariable("tour_id") long tourId) throws Exception{
        try {
            return tourFacade.getTourParticipants(tourId);
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Getting avg. rating for tour
     * @param tourId id of the tour
     * @return value of avg. rating
     * @throws Exception {@link ResourceNotFoundException}
     */
    @RequestMapping(value = "/tourRating/{tour_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final double getTourRating(@PathVariable("tour_id") long tourId) throws Exception{
        try {
            return tourFacade.getTourRating(tourId);
        } catch (Exception e){
            throw new ResourceNotFoundException();
        }
    }


}
