package io.coremaker.internship.posmonitoring.domain.model.command;

import lombok.Data;

import java.time.Instant;

@Data
public class CreatePosDeviceCommand {
    String deviceId;
    String location;
    String provider;
    Boolean online;
    Instant lastActivity;
}
