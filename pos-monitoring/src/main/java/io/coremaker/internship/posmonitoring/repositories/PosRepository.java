package io.coremaker.internship.posmonitoring.repositories;

import io.coremaker.internship.posmonitoring.domain.PosDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosRepository extends JpaRepository<PosDevice, Long> {
    Page<PosDevice> findAll(Pageable pageable);

    Page<PosDevice> findByOnline(Boolean online, Pageable pageable);

    Page<PosDevice> findByProvider(String provider, Pageable pageable);

    Page<PosDevice> findByOnlineAndProvider(Boolean online, String provider, Pageable pageable);
}
