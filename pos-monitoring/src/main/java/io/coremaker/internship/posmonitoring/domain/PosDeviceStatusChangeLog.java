package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Data
public class PosDeviceStatusChangeLog {
    @Id
    @GeneratedValue
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "id")
//    private PosDevice posDeviceId;

    private Boolean online;
    private Instant createdAt;
    private Instant updatedAt;

}
