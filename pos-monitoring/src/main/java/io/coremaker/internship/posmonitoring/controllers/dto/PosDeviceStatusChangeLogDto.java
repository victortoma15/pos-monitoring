package io.coremaker.internship.posmonitoring.controllers.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class PosDeviceStatusChangeLogDto {
    private Long deviceId;
    private Boolean online;
    private Instant createdAt;
}
