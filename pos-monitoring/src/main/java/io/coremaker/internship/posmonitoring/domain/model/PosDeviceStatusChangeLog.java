package io.coremaker.internship.posmonitoring.domain.model;

import lombok.Data;

import java.time.Instant;

@Data
public class PosDeviceStatusChangeLog {

    private PosDevice posDevice;

    private Boolean online;
    private Instant createdAt;
    private Instant updatedAt;

}
