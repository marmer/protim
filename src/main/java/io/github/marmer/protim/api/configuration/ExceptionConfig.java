package io.github.marmer.protim.api.configuration;

import io.github.marmer.protim.service.exception.RessourceConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class ExceptionConfig {
    @ExceptionHandler(RessourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ProtimErrorDTO handleResourceConflict(final RessourceConflictException e) {
        return new ProtimErrorDTO(e);
    }

}
