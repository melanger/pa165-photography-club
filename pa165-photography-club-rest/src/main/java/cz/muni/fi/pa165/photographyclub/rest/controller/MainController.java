package cz.muni.fi.pa165.photographyclub.rest.controller;

import cz.muni.fi.pa165.photographyclub.rest.ApiUris;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    /**
     * The main entry point of the REST API
     * Provides access to all the resources with links to resource URIs
     * 
     * @return resources uris
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<>();
        
        resourcesMap.put("equipment_uri", ApiUris.ROOT_URI_EQUIPMENT);
        resourcesMap.put("members_uri", ApiUris.ROOT_URI_MEMBERS);
        resourcesMap.put("reviews_uri", ApiUris.ROOT_URI_REVIEWS);
        resourcesMap.put("tours_uri", ApiUris.ROOT_URI_TOURS);
        
        return Collections.unmodifiableMap(resourcesMap);
        
    }
}