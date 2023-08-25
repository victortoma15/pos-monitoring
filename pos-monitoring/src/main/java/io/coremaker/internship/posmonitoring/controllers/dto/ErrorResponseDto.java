package io.coremaker.internship.posmonitoring.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class ErrorResponseDto {
    String error;
    Instant timestamp = Instant.now();

    public ErrorResponseDto(String error) {
        this.error = error;
    }
}
