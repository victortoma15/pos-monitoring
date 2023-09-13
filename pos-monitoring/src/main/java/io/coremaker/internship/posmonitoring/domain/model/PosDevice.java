package io.coremaker.internship.posmonitoring.domain.model;

import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceNotFoundException;
import io.coremaker.internship.posmonitoring.domain.model.command.CreatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.model.command.UpdatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.port.PosDeviceRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PosDevice {


    private Long id;
    private String deviceId;
    private String location;
    private Boolean online;
    private String provider;
    private Instant lastActivity;
    private Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private List<PosDeviceStatusChangeLog> statusChangeLogs = new ArrayList<>();

    public void recordStatusChangeLog(final PosDeviceStatusChangeLog statusChangeLog) {
        statusChangeLogs.add(statusChangeLog);
        statusChangeLog.setPosDevice(this);
    }

    public static PosDevice create(CreatePosDeviceCommand command, PosDeviceRepositoryPort posDeviceRepositoryPort) {
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

    public static void delete(Long id, PosDeviceRepositoryPort posDeviceRepositoryPort) {
        if (!posDeviceRepositoryPort.existsById(id)) {
            throw new PosDeviceNotFoundException(id);
        }
        posDeviceRepositoryPort.delete(id);
    }

    public static PosDevice getById(Long id, PosDeviceRepositoryPort posDeviceRepositoryPort) {
        Optional<PosDevice> posDevice = posDeviceRepositoryPort.getDeviceDetails(id);
        if (posDevice.isEmpty()) {
            throw new PosDeviceNotFoundException(id);
        }
        return posDevice.get();
    }

    public PosDevice update(UpdatePosDeviceCommand command, PosDeviceRepositoryPort posDeviceRepositoryPort) {
        online = command.getOnline();
        lastActivity = command.getLastActivity();
        PosDeviceStatusChangeLog posDeviceStatusChangeLog = new PosDeviceStatusChangeLog();
        posDeviceStatusChangeLog.setOnline(online);
        recordStatusChangeLog(posDeviceStatusChangeLog);
        return posDeviceRepositoryPort.update(this);
    }

}
