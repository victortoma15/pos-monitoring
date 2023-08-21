package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table (name = "pos_device_status_change_log")
@Data
public class PosDeviceStatusChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id")
    private PosDevice posDeviceId;

    private Boolean online;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}
