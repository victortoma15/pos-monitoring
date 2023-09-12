package io.coremaker.internship.posmonitoring.services;

import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceNotFoundException;
import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.CreatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.PosDeviceResponseDto;
import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.PosDeviceStatusChangeLogDto;
import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.UpdatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.domain.model.PosDeviceStatusChangeLog;
import io.coremaker.internship.posmonitoring.infrastructure.PosDeviceRepository;
import io.coremaker.internship.posmonitoring.infrastructure.PosDeviceStatusChangeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PosService {

    private final PosDeviceRepository posDeviceRepository;
    private final PosDeviceStatusChangeLogRepository posDeviceStatusChangeLogRepository;


//    public PosDeviceResponseDto createPosDevice(CreatePosDeviceRequestDto posDevice) {
//        final PosDevice newPosDevice = mapFrom(posDevice);
//        final PosDevice createdPosDevice = posDeviceRepository.save(newPosDevice);
//        return mapFrom(createdPosDevice);
  //  }

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
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }


    public void deletePosDevice(Long id) {
        try {
            posDeviceRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PosDeviceNotFoundException(id);
        }
    }

//    public PosDeviceResponseDto getPosDevice(Long id) {
//          final Optional<PosDevice> optionalPosDevice = posDeviceRepository.findById(id);
//        if (optionalPosDevice.isEmpty()) {
//            throw new PosDeviceNotFoundException(id);
//        }
//        PosDevice posDevice = optionalPosDevice.get();
//        return mapFrom(posDevice);
//    }

    private void map(final UpdatePosDeviceRequestDto dto, PosDevice entity) {
        entity.setOnline(dto.getOnline());
        entity.setLastActivity(dto.getLastActivity());
    }

//    @Transactional
//    public PosDeviceResponseDto updatePosDevice(Long id, UpdatePosDeviceRequestDto updatedPosDevice) {
//        Optional<PosDevice> optionalPosDevice = posDeviceRepository.findById(id);
//        if (optionalPosDevice.isEmpty()) {
//            throw new PosDeviceNotFoundException(id);
//        }
//        PosDevice existingPosDevice = optionalPosDevice.get();
//        map(updatedPosDevice, existingPosDevice);
//
//        final PosDeviceStatusChangeLog statusChangeLog = new PosDeviceStatusChangeLog();
//        statusChangeLog.setOnline(existingPosDevice.getOnline());
//
//        existingPosDevice.recordStatusChangeLog(statusChangeLog);
//
//        final PosDevice updatedDevice = posDeviceRepository.save(existingPosDevice);
//        return mapFrom(updatedDevice);
//    }

    private Pageable createPageable(int pageNo, int size, Sort.Direction sortDirection) {
        return PageRequest.of(pageNo, size, Sort.by(sortDirection, "createdAt"));
    }

    private List<PosDeviceResponseDto> buildResponse(Page<PosDevice> pageOfPosDevices) {
        List<PosDeviceResponseDto> responseDtos = new ArrayList<>();

        for (PosDevice device : pageOfPosDevices.getContent()) {
            responseDtos.add(mapFrom(device));
        }
        return responseDtos;
    }

    private List<PosDeviceStatusChangeLogDto> buildStatusChangeLogResponse(Page<PosDeviceStatusChangeLog> pageOfPosDevicesStatusLog) {
        List<PosDeviceStatusChangeLogDto> responseDtos = new ArrayList<>();

        for (PosDeviceStatusChangeLog deviceStatusLog : pageOfPosDevicesStatusLog.getContent()) {
            responseDtos.add(mapFromStatusChangeLog(deviceStatusLog));
        }

        return responseDtos;
    }

//    public List<PosDeviceResponseDto> getAllPosDevices(int pageNo, int size) {
//        Page<PosDevice> devices = posDeviceRepository.findAll(createPageable(pageNo, size, Sort.Direction.DESC));
//        return buildResponse(devices);
//
//    }
//
//    public List<PosDeviceResponseDto> getPosDevicesByStatus(Boolean online, int pageNo, int size) {
//        Page<PosDevice> devices = posDeviceRepository.findByOnline(online, createPageable(pageNo, size, Sort.Direction.DESC));
//        return buildResponse(devices);
//    }
//
//    public List<PosDeviceResponseDto> getPosDevicesByProvider(String provider, int pageNo, int size) {
//        Page<PosDevice> devices = posDeviceRepository.findByProvider(provider, createPageable(pageNo, size, Sort.Direction.DESC));
//        return buildResponse(devices);
//    }
//
//    public List<PosDeviceResponseDto> getPosDevicesByStatusAndProvider(Boolean online, String provider, int pageNo, int size) {
//        Page<PosDevice> devices = posDeviceRepository.findByOnlineAndProvider(online, provider, createPageable(pageNo, size, Sort.Direction.DESC));
//        return buildResponse(devices);
//    }

    private PosDeviceStatusChangeLogDto mapFromStatusChangeLog(PosDeviceStatusChangeLog log) {
        PosDeviceStatusChangeLogDto logDto = new PosDeviceStatusChangeLogDto();
        logDto.setDeviceId(log.getPosDevice().getId());
        logDto.setOnline(log.getOnline());
        logDto.setCreatedAt(log.getCreatedAt());
        return logDto;
    }

    public List<PosDeviceStatusChangeLogDto> getStatusChangeLogsForDevice(Long deviceId, int pageNo, int size) {
        Page<PosDeviceStatusChangeLog> devices = posDeviceStatusChangeLogRepository.findByPosDevice_Id(deviceId, createPageable(pageNo, size, Sort.Direction.DESC));
        return buildStatusChangeLogResponse(devices);
    }


}
