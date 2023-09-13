package io.coremaker.internship.posmonitoring.application.rest.posdevice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponseDto {
    String error;
}
