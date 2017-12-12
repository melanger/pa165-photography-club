package cz.muni.fi.pa165.photographyclub.rest;

/**
 * Represents the entry points for the API
 * this list can be increased so that it contains all the 
 * other URIs also for the sub-resources so that it can 
 * reused globally from all the controllers
 */
public abstract class ApiUris {
    public static final String ROOT_URI_EQUIPMENT   = "/equipment"; 
    public static final String ROOT_URI_MEMBERS      = "/members";
    public static final String ROOT_URI_REVIEWS     = "/reviews";
    public static final String ROOT_URI_TOURS = "/tours";  
}
