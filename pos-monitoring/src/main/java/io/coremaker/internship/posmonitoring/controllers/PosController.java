package io.coremaker.internship.posmonitoring.controllers;

import io.coremaker.internship.posmonitoring.controllers.dto.CreatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.controllers.dto.PosDeviceResponseDto;
import io.coremaker.internship.posmonitoring.controllers.dto.UpdatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.services.PosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/devices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PosController {

    private final PosService posService;

    @PostMapping
    PosDeviceResponseDto createPosDevice(@RequestBody @Valid CreatePosDeviceRequestDto body) {
        return posService.createPosDevice(body);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePosDevice(@PathVariable Long id) {
        posService.deletePosDevice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<PosDeviceResponseDto> getPosDevice(@PathVariable Long id) {
        PosDeviceResponseDto posDevice = posService.getPosDevice(id);
        return ResponseEntity.ok(posDevice);
    }
    @PutMapping("/{id}")
    PosDeviceResponseDto updatePosDevice(@PathVariable Long id, @RequestBody @Valid UpdatePosDeviceRequestDto body) {
        return posService.updatePosDevice(id, body);
    }

}
