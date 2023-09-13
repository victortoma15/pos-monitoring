package io.coremaker.internship.posmonitoring.application;

import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceAlreadyExistsException;
import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceNotFoundException;
import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.domain.model.command.CreatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.model.command.UpdatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.port.PosDeviceRepositoryPort;
import io.coremaker.internship.posmonitoring.domain.port.PosDeviceServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PosDeviceServicePortAdapter implements PosDeviceServicePort {

    private final PosDeviceRepositoryPort posDeviceRepositoryPort;

    @Override
    public PosDevice create(CreatePosDeviceCommand command) {
        try{
            return PosDevice.create(command, posDeviceRepositoryPort);
        }
        catch (RuntimeException e){
            throw new PosDeviceAlreadyExistsException(command.getDeviceId());
        }

    }

    @Override
    public PosDevice update(UpdatePosDeviceCommand command) {
        Optional<PosDevice> posDevice = posDeviceRepositoryPort.getDeviceDetails(command.getId());
        if (posDevice.isPresent()) {
            return posDevice.get().update(command, posDeviceRepositoryPort);
        } else {
            throw new PosDeviceNotFoundException(command.getId());
        }
    }

    @Override
    public void delete(Long id) {
        PosDevice.delete(id, posDeviceRepositoryPort);
    }

    @Override
    public PosDevice getById(Long id) {
        return PosDevice.getById(id, posDeviceRepositoryPort);
    }
}
