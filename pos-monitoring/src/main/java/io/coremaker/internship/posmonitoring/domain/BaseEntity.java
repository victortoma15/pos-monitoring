package io.coremaker.internship.posmonitoring.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

public abstract class BaseEntity {
    protected Instant createdAt;
    protected Instant updatedAt;
    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
