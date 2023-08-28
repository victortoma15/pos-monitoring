package io.coremaker.internship.posmonitoring.services;

import io.coremaker.internship.posmonitoring.controllers.DeviceNotFoundException;
import io.coremaker.internship.posmonitoring.controllers.dto.CreatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.controllers.dto.PosDeviceResponseDto;
import io.coremaker.internship.posmonitoring.domain.PosDevice;
import io.coremaker.internship.posmonitoring.repositories.PosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PosService {

    private final PosRepository posRepository;


    public PosDeviceResponseDto createPosDevice(CreatePosDeviceRequestDto posDevice) {
        final PosDevice newPosDevice = mapFrom(posDevice);
        final PosDevice createdPosDevice = posRepository.save(newPosDevice);
        return mapFrom(createdPosDevice);
    }

    private PosDevice mapFrom(final CreatePosDeviceRequestDto dto) {
        final PosDevice entity = new PosDevice();
        entity.setDeviceId(dto.getDeviceId());
        entity.setProvider(dto.getProvider());
        entity.setLocation(dto.getLocation());
        entity.setOnline(dto.getOnline());
        entity.setLastActivity(dto.getLastActivity());
        return entity;
    }

    private PosDeviceResponseDto mapFrom(final PosDevice entity) {
        final PosDeviceResponseDto dto = new PosDeviceResponseDto();
        dto.setId(entity.getId());
        dto.setDeviceId(entity.getDeviceId());
        dto.setLocation(entity.getLocation());
        dto.setOnline(entity.getOnline());
        dto.setProvider(entity.getProvider());
        dto.setLastActivity(entity.getLastActivity());
        return dto;
    }


    public void deletePosDevice(Long id) {
        try {
            posRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DeviceNotFoundException(id);
        }
    }

}
