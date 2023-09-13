package io.coremaker.internship.posmonitoring.application.rest.posdevice;

import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.ErrorResponseDto;
import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceAlreadyExistsException;
import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception e) {
        log.error("Internal server error!", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.builder()
                        .error("Something went wrong!")
                        .build()
                );
    }

    @ExceptionHandler(PosDeviceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleDeviceNotFoundException(PosDeviceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto.builder()
                        .error(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(PosDeviceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleDeviceAlreadyExistsException(PosDeviceAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponseDto.builder()
                        .error(e.getMessage())
                        .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Something went wrong!", ex);
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}


