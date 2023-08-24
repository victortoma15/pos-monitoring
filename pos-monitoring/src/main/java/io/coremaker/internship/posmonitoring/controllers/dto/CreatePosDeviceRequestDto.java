package io.coremaker.internship.posmonitoring.controllers.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class CreatePosDeviceRequestDto {

    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Instant lastActivity;

}
