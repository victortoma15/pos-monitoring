package io.coremaker.internship.posmonitoring.repositories;

import io.coremaker.internship.posmonitoring.domain.PosDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosRepository extends JpaRepository<PosDevice, Long> {
    Page<PosDevice> findByOnline(Boolean online, Pageable pageable);

    Page<PosDevice> findByProvider(String provider, Pageable pageable);
}
