package io.coremaker.internship.posmonitoring.infrastructure;

import io.coremaker.internship.posmonitoring.domain.model.PosDeviceStatusChangeLog;
import io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter.PosDeviceStatusChangeLogJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosDeviceStatusChangeLogRepository extends JpaRepository<PosDeviceStatusChangeLogJpa, Long> {
    Page<PosDeviceStatusChangeLog> findByPosDevice_Id(Long id, Pageable pageable);
}
