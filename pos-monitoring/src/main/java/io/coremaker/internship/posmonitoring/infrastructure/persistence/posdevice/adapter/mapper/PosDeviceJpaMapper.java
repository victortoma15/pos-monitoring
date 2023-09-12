package io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter.mapper;

import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.infrastructure.PosDeviceRepository;
import io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter.PosDeviceJpa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PosDeviceJpaMapper {
   // @Mapping(target = "deviceId", source = "deviceId")
    PosDeviceJpa toPosDeviceJpa(PosDevice posDevice);


}
