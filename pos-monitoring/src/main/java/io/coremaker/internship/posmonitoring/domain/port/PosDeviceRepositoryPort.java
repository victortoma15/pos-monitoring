package io.coremaker.internship.posmonitoring.domain.port;

import io.coremaker.internship.posmonitoring.domain.model.PosDevice;

import java.util.Optional;

public interface PosDeviceRepositoryPort {
    PosDevice create(PosDevice posDevice);
    boolean existsByDeviceIdAndProvider(String deviceId, String provider);
    boolean existsById(Long id);
    PosDevice update(PosDevice posDevice);
    void delete(Long id);
    Optional <PosDevice> getDeviceDetails(Long id);

}
