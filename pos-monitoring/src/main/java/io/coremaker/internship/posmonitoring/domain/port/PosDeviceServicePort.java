package io.coremaker.internship.posmonitoring.domain.port;

import io.coremaker.internship.posmonitoring.domain.model.command.CreatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.domain.model.command.UpdatePosDeviceCommand;

public interface PosDeviceServicePort {
    PosDevice create(CreatePosDeviceCommand command);
    PosDevice update(UpdatePosDeviceCommand command);
    void delete(Long id);
    PosDevice getById(Long id);
}
