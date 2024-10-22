package io.coremaker.internship.posmonitoring.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class PosNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PosNotFoundException.class)
    String posNotFoundHandler(PosNotFoundException ex) {
        return ex.getMessage();
    }
}
