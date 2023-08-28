package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class PosDeviceStatusChangeLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_device_id")
    private PosDevice posDevice;

    private Boolean online;

}
