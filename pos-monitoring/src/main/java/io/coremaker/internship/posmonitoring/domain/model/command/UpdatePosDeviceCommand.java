package io.coremaker.internship.posmonitoring.domain.model.command;

import lombok.Data;

import java.time.Instant;

@Data
public class UpdatePosDeviceCommand {
    Long id;
    Boolean online;
    Instant lastActivity;
}
