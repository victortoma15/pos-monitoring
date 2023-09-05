package io.coremaker.internship.posmonitoring.controllers;

import io.coremaker.internship.posmonitoring.controllers.dto.CreatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.controllers.dto.PosDeviceResponseDto;
import io.coremaker.internship.posmonitoring.controllers.dto.PosDeviceStatusChangeLogDto;
import io.coremaker.internship.posmonitoring.controllers.dto.UpdatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.services.PosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/devices")
public class PosController {

    private final PosService posService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    PosDeviceResponseDto createPosDevice(@RequestBody @Valid CreatePosDeviceRequestDto body) {
        return posService.createPosDevice(body);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deletePosDevice(@PathVariable Long id) {
        posService.deletePosDevice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PosDeviceResponseDto> getPosDevice(@PathVariable Long id) {
        PosDeviceResponseDto posDevice = posService.getPosDevice(id);
        return ResponseEntity.ok(posDevice);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    PosDeviceResponseDto updatePosDevice(@PathVariable Long id, @RequestBody @Valid UpdatePosDeviceRequestDto body) {
        return posService.updatePosDevice(id, body);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<PosDeviceResponseDto> getDevices(@RequestParam(required = false) Boolean online, @RequestParam(required = false) String provider,
                                          @RequestParam(required = false, defaultValue = "0") int pageNo,
                                          @RequestParam(required = false, defaultValue = "30") int size) {

        List<PosDeviceResponseDto> devices;
        if (online != null && provider != null) {
            devices = posService.getPosDevicesByStatusAndProvider(online, provider, pageNo, size);
        } else if (online != null) {
            devices = posService.getPosDevicesByStatus(online, pageNo, size);
        } else if (provider != null) {
            devices = posService.getPosDevicesByProvider(provider, pageNo, size);
        } else {
            devices = posService.getAllPosDevices(pageNo, size);
        }

        return devices;
    }

    @GetMapping(path = "/{id}/statuslog", produces = MediaType.APPLICATION_JSON_VALUE)
    List<PosDeviceStatusChangeLogDto> getStatusChangeLogsForDevice(@PathVariable("id") Long id, @RequestParam(required = false, defaultValue = "0") int pageNo,
                                                                   @RequestParam(required = false, defaultValue = "30") int size) {
        return posService.getStatusChangeLogsForDevice(id, pageNo, size);
    }

}
