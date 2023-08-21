package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table
@Data
public class PosDevice {

    @Id
    @GeneratedValue
    private Long id;

    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Instant lastActivity;
    private Instant createdAt;
    private Instant updatedAt;

}
