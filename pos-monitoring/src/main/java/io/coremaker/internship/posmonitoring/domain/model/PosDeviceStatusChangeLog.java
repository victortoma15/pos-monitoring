package io.coremaker.internship.posmonitoring.domain.model;

import io.coremaker.internship.posmonitoring.domain.model.BaseEntity;
import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PosDeviceStatusChangeLog extends BaseEntity {

    private PosDevice posDevice;

    private Boolean online;

}
