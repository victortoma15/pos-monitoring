package io.coremaker.internship.posmonitoring.controllers.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class CreatePosDeviceRequestDto {
    @NotBlank
    private String deviceId;
    @NotBlank
    private String location;
    @NotNull
    private Boolean online;
    @NotBlank
    private String provider;
    @NotNull
    private Instant lastActivity;

}
