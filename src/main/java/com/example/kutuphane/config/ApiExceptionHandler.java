package com.example.kutuphane.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ BadRequestException.class })
    public String entityNotFound() {
        return "/hata";
    }
}
