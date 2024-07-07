package com.example.processor.advice;

import com.example.processor.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Harshil Makwana
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Map<String,Object>> generalException(
            Exception ex, WebRequest request) {
        Map<String,Object> errorInfo = new LinkedHashMap<>();
        errorInfo.put("errorMessage",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<Map<String,Object>>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<String> serviceException(
            ServiceException ex, WebRequest request) {
        Map<String,Object> errorInfo = new LinkedHashMap<>();
        errorInfo.put("file",ex.getFile());
        errorInfo.put("error",ex.getErrorMessage());
        return new ResponseEntity<String>(ex.getErrorMessage(),HttpStatus.valueOf(ex.getStatusCode()));
    }
}
