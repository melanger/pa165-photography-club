package cz.muni.fi.pa165.photographyclub.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason="The requested resource was not modified")
public class ResourceNotModifiedException extends RuntimeException {
    
}