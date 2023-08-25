package io.coremaker.internship.posmonitoring.repositories;

import io.coremaker.internship.posmonitoring.domain.PosDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosRepository extends JpaRepository<PosDevice, Long> {

}
