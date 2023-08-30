package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class PosDevice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Instant lastActivity;

    @OneToMany(mappedBy = "posDevice", orphanRemoval = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<PosDeviceStatusChangeLog> statusChangeLogs = new ArrayList<>();

    public void recordStatusChangeLog(final PosDeviceStatusChangeLog statusChangeLog) {
        statusChangeLogs.add(statusChangeLog);
        statusChangeLog.setPosDevice(this);
    }

}
