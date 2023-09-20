package io.coremaker.internship.posmonitoring.infrastructure;

import io.coremaker.internship.posmonitoring.infrastructure.persistence.posdevice.adapter.PosDeviceJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosDeviceRepository extends JpaRepository<PosDeviceJpa, Long> {
    Page<PosDeviceJpa> findAll(Pageable pageable);

    Page<PosDeviceJpa> findByOnline(Boolean online, Pageable pageable);

    Page<PosDeviceJpa> findByProvider(String provider, Pageable pageable);

    Page<PosDeviceJpa> findByOnlineAndProvider(Boolean online, String provider, Pageable pageable);

    boolean existsByDeviceIdAndProvider(String deviceId, String provider);

}
