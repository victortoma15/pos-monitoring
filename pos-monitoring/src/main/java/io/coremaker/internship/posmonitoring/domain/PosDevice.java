package io.coremaker.internship.posmonitoring.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table
@Data
public class PosDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Timestamp lastActivity;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
