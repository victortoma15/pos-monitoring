package io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter;

import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.domain.model.PosDeviceStatusChangeLog;
import io.coremaker.internship.posmonitoring.domain.port.PosDeviceRepositoryPort;
import io.coremaker.internship.posmonitoring.infrastructure.PosDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PosDeviceRepositoryPortAdapter implements PosDeviceRepositoryPort {

    private final PosDeviceRepository posDeviceRepository;

    @Override
    public PosDevice create(PosDevice posDevice) {
        PosDeviceJpa posDeviceJpa = new PosDeviceJpa();
        posDeviceJpa.setDeviceId(posDevice.getDeviceId());
        posDeviceJpa.setLocation(posDevice.getLocation());
        posDeviceJpa.setProvider(posDevice.getProvider());
        posDeviceJpa.setOnline(posDevice.getOnline());
        posDeviceJpa.setLastActivity(posDevice.getLastActivity());
        PosDeviceJpa newPosDeviceJpa = posDeviceRepository.save(posDeviceJpa);
        PosDevice newPosDevice = new PosDevice();
        newPosDevice.setId(newPosDeviceJpa.getId());
        newPosDevice.setDeviceId(newPosDeviceJpa.getDeviceId());
        newPosDevice.setLocation(newPosDeviceJpa.getLocation());
        newPosDevice.setProvider(newPosDeviceJpa.getProvider());
        newPosDevice.setOnline(newPosDeviceJpa.getOnline());
        newPosDevice.setLastActivity(newPosDeviceJpa.getLastActivity());
        newPosDevice.setCreatedAt(newPosDeviceJpa.getCreatedAt());
        newPosDevice.setUpdatedAt(newPosDeviceJpa.getUpdatedAt());

        return newPosDevice;
    }

    @Override
    public boolean existsByDeviceIdAndProvider(String deviceId, String provider) {
        return posDeviceRepository.existsByDeviceIdAndProvider(deviceId, provider);
    }

    @Override
    public boolean existsById(Long id) {
        return posDeviceRepository.existsById(id);
    }

    @Override
    public PosDevice update(PosDevice posDevice) {
        PosDeviceJpa posDeviceJpa = new PosDeviceJpa();
        posDeviceJpa.setId(posDevice.getId());
        posDeviceJpa.setDeviceId(posDevice.getDeviceId());
        posDeviceJpa.setLocation(posDevice.getLocation());
        posDeviceJpa.setProvider(posDevice.getProvider());
        posDeviceJpa.setOnline(posDevice.getOnline());
        posDeviceJpa.setLastActivity(posDevice.getLastActivity());
        posDeviceJpa.setCreatedAt(posDevice.getCreatedAt());
        posDeviceJpa.setUpdatedAt(posDevice.getUpdatedAt());
        for (PosDeviceStatusChangeLog statusChangeLog : posDevice.getStatusChangeLogs()) {
            PosDeviceStatusChangeLogJpa statusChangeLogJpa = new PosDeviceStatusChangeLogJpa();
            statusChangeLogJpa.setPosDevice(posDeviceJpa);
            statusChangeLogJpa.setOnline(statusChangeLog.getOnline());
            posDeviceJpa.getStatusChangeLogs().add(statusChangeLogJpa);
        }
        PosDeviceJpa updatedPosDeviceJpa = posDeviceRepository.save(posDeviceJpa);
        PosDevice updatedPosDevice = new PosDevice();
        updatedPosDevice.setId(updatedPosDeviceJpa.getId());
        updatedPosDevice.setDeviceId(updatedPosDeviceJpa.getDeviceId());
        updatedPosDevice.setLocation(updatedPosDeviceJpa.getLocation());
        updatedPosDevice.setProvider(updatedPosDeviceJpa.getProvider());
        updatedPosDevice.setOnline(updatedPosDeviceJpa.getOnline());
        updatedPosDevice.setLastActivity(updatedPosDeviceJpa.getLastActivity());
        updatedPosDevice.setCreatedAt(updatedPosDeviceJpa.getCreatedAt());
        updatedPosDevice.setUpdatedAt(updatedPosDeviceJpa.getUpdatedAt());

        return updatedPosDevice;
    }

    @Override
    public void delete(Long id) {
        posDeviceRepository.deleteById(id);
    }

    @Override
    public Optional<PosDevice> getDeviceDetails(Long id) {

        Optional<PosDeviceJpa> posDeviceJpa = posDeviceRepository.findById(id);
        if (posDeviceJpa.isPresent()) {
            PosDevice posDevice = new PosDevice();
            posDevice.setId(posDeviceJpa.get().getId());
            posDevice.setDeviceId(posDeviceJpa.get().getDeviceId());
            posDevice.setLocation(posDeviceJpa.get().getLocation());
            posDevice.setProvider(posDeviceJpa.get().getProvider());
            posDevice.setOnline(posDeviceJpa.get().getOnline());
            posDevice.setLastActivity(posDeviceJpa.get().getLastActivity());
            posDevice.setCreatedAt(posDeviceJpa.get().getCreatedAt());
            posDevice.setUpdatedAt(posDeviceJpa.get().getUpdatedAt());
            return Optional.of(posDevice);
        } else {
            return Optional.empty();
        }
    }
}