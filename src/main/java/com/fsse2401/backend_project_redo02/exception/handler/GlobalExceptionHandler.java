package com.fsse2401.backend_project_redo02.exception.handler;

import com.fsse2401.backend_project_redo02.exception.DataMissingException;
import com.fsse2401.backend_project_redo02.exception.InvalidInputException;
import com.fsse2401.backend_project_redo02.exception.errorResponse.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleErrorResponse(NullPointerException e){
        logger.warn(e.toString());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamps(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND);
        errorResponse.setPath(e.toString());
        return errorResponse;
    }

    @ExceptionHandler(DataMissingException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleErrorResponse(DataMissingException e){
        logger.warn(e.toString());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamps(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST);
        errorResponse.setPath(e.toString());
        return errorResponse;
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleErrorResponse(InvalidInputException e){
        logger.warn(e.toString());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamps(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST);
        errorResponse.setPath(e.toString());
        return errorResponse;
    }
}
