package io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pos_device_status_change_log")
@Data
public class PosDeviceStatusChangeLogJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_device_id")
    private PosDeviceJpa posDevice;

    private Boolean online;
}
