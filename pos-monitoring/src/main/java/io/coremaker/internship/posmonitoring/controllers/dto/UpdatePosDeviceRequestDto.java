package io.coremaker.internship.posmonitoring.controllers.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class UpdatePosDeviceRequestDto {
    @NotNull
    private Boolean online;
    @NotNull
    private Instant lastActivity;
}
