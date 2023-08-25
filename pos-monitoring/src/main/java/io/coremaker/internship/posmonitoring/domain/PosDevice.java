package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Data
public class PosDevice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Instant lastActivity;

    @OneToMany(mappedBy = "posDevice", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PosDeviceStatusChangeLog> statusChangeLogs = new ArrayList<>();

}
