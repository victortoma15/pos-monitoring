package io.coremaker.internship.posmonitoring.domain.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@Data
public abstract class BaseEntity {
    protected Instant createdAt;
    protected Instant updatedAt;

}
