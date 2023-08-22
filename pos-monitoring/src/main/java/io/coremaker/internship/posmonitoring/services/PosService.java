package io.coremaker.internship.posmonitoring.services;

import io.coremaker.internship.posmonitoring.domain.PosDevice;
import io.coremaker.internship.posmonitoring.repositories.PosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosService {

    private final PosRepository posRepository;

    @Autowired
    public PosService(PosRepository posRepository) {
        this.posRepository = posRepository;
    }

    public PosDevice createPosDevice(PosDevice posDevice) {
        return posRepository.save(posDevice);
    }

}
