package io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pos_device")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PosDeviceJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Instant lastActivity;
    private Instant createdAt;
    private Instant updatedAt;

    @OneToMany(mappedBy = "posDevice", orphanRemoval = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<PosDeviceStatusChangeLogJpa> statusChangeLogs = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}