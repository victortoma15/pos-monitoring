package io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter.mapper;

import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter.PosDeviceJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PosDeviceJpaMapper {

    PosDeviceJpa toJpa(PosDevice posDevice);


}
