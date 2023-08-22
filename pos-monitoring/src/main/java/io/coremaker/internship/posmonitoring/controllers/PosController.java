package io.coremaker.internship.posmonitoring.controllers;

import io.coremaker.internship.posmonitoring.domain.PosDevice;
import io.coremaker.internship.posmonitoring.repositories.PosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PosController {

    private final PosRepository posRepository;

    @Autowired
    public PosController(PosRepository posRepository) {
        this.posRepository = posRepository;
    }

    @PostMapping("/pos")
    PosDevice newPosDevice(@RequestBody PosDevice newPosDevice) {
        return posRepository.save(newPosDevice);
    }

}
