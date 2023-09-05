package io.coremaker.internship.posmonitoring.repositories;

import io.coremaker.internship.posmonitoring.domain.PosDeviceStatusChangeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusChangeLogRepository extends JpaRepository<PosDeviceStatusChangeLog, Long> {
    Page<PosDeviceStatusChangeLog> findByPosDevice_Id(Long id, Pageable pageable);
}
