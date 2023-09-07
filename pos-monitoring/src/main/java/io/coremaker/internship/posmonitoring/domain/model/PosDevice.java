package io.coremaker.internship.posmonitoring.domain.model;

import io.coremaker.internship.posmonitoring.controllers.DeviceNotFoundException;
import io.coremaker.internship.posmonitoring.domain.model.command.CreatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.model.command.UpdatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.port.PosDeviceRepositoryPort;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@EqualsAndHashCode(callSuper = true)
public class PosDevice extends BaseEntity {


    private Long id;
    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Instant lastActivity;

    private List<PosDeviceStatusChangeLog> statusChangeLogs = new ArrayList<>();

    private void recordStatusChangeLog(final PosDeviceStatusChangeLog statusChangeLog) {
        statusChangeLogs.add(statusChangeLog);
        statusChangeLog.setPosDevice(this);
    }

    static PosDevice create(CreatePosDeviceCommand command, PosDeviceRepositoryPort posDeviceRepositoryPort) {
        if (posDeviceRepositoryPort.existsByDeviceIdAndProvider(command.getDeviceId(), command.getProvider())) {
            throw new RuntimeException("Already exists");
        }
        PosDevice posDevice = new PosDevice();
        posDevice.setDeviceId(command.getDeviceId());
        posDevice.setLocation(command.getLocation());
        posDevice.setProvider(command.getProvider());
        posDevice.setOnline(command.getOnline());
        posDevice.setLastActivity(command.getLastActivity());

        return posDeviceRepositoryPort.create(posDevice);
    }

    static void delete(Long id, PosDeviceRepositoryPort posDeviceRepositoryPort) {
        if (!posDeviceRepositoryPort.existsById(id)) {
            throw new DeviceNotFoundException(id);
        }
        posDeviceRepositoryPort.delete(id);
    }

    static PosDevice getById(Long id, PosDeviceRepositoryPort posDeviceRepositoryPort) {
        Optional<PosDevice> posDevice = posDeviceRepositoryPort.getDeviceDetails(id);
        if (posDevice.isEmpty()) {
            throw new DeviceNotFoundException(id);
        }
        return posDevice.get();
    }

    PosDevice update(UpdatePosDeviceCommand command, PosDeviceRepositoryPort posDeviceRepositoryPort) {
        PosDeviceStatusChangeLog posDeviceStatusChangeLog = new PosDeviceStatusChangeLog();
        online = command.getOnline();
        lastActivity = command.getLastActivity();
        posDeviceStatusChangeLog.setOnline(online);
        recordStatusChangeLog(posDeviceStatusChangeLog);
        return posDeviceRepositoryPort.update(this);
    }

}
