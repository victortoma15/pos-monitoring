package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Data
@Getter
@Setter
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

    @OneToMany(mappedBy = "posDevice", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PosDeviceStatusChangeLog> statusChangeLogs = new ArrayList<>();

}
