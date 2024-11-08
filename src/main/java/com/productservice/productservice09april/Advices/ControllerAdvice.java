package com.productservice.productservice09april.Advices;

import com.productservice.productservice09april.Exception.InvalidTokenException;
import com.productservice.productservice09april.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorDTO> handleInvalidTokenException(InvalidTokenException exception){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());

        return new ResponseEntity<>(
                errorDTO,
                HttpStatus.NOT_FOUND
        );
    }
}
