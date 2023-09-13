package io.coremaker.internship.posmonitoring.application.rest.posdevice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class UpdatePosDeviceRequestDto {
    @NotNull
    private Boolean online;
    @NotNull
    private Instant lastActivity;
}
