package cz.muni.fi.pa165.photographyclub.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class LoginNotSuccessfulException extends RuntimeException {
    
}