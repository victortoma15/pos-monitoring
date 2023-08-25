package io.coremaker.internship.posmonitoring.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponseDto {
    String error;
    Instant timestamp = Instant.now();

    public ErrorResponseDto(String error) {
        this.error = error;
    }
}
