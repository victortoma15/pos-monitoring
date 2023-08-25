package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Data
public class PosDeviceStatusChangeLog extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_device_id")
    private PosDevice posDevice;

    private Boolean online;

}
