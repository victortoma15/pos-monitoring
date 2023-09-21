package io.coremaker.internship.posmonitoring.application.rest.posdevice;

import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.PosDeviceResponseDto;
import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.PosDeviceStatusChangeLogDto;
import io.coremaker.internship.posmonitoring.application.rest.posdevice.dto.UpdatePosDeviceRequestDto;
import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceNotFoundException;
import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.domain.model.command.UpdatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.port.PosDeviceServicePort;
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
public class PosDeviceController {

    private final PosDeviceServicePort posDeviceServicePort;

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deletePosDevice(@PathVariable Long id) {
        posDeviceServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    PosDeviceResponseDto updatePosDevice(@PathVariable Long id, @RequestBody @Valid UpdatePosDeviceRequestDto body) {
        UpdatePosDeviceCommand command = new UpdatePosDeviceCommand();
        command.setId(id);
        command.setOnline(body.getOnline());
        command.setLastActivity(body.getLastActivity());
        PosDevice updatedPosDevice = posDeviceServicePort.update(command);
        PosDeviceResponseDto posDeviceResponseDto = new PosDeviceResponseDto();
        posDeviceResponseDto.setId(updatedPosDevice.getId());
        posDeviceResponseDto.setDeviceId(updatedPosDevice.getDeviceId());
        posDeviceResponseDto.setLocation(updatedPosDevice.getLocation());
        posDeviceResponseDto.setProvider(updatedPosDevice.getProvider());
        posDeviceResponseDto.setOnline(updatedPosDevice.getOnline());
        posDeviceResponseDto.setLastActivity(updatedPosDevice.getLastActivity());
        posDeviceResponseDto.setCreatedAt(updatedPosDevice.getCreatedAt());
        posDeviceResponseDto.setUpdatedAt(updatedPosDevice.getUpdatedAt());
        return posDeviceResponseDto;
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    List<PosDeviceResponseDto> getDevices(@RequestParam(required = false) Boolean online, @RequestParam(required = false) String provider,
//                                          @RequestParam(required = false, defaultValue = "0") int pageNo,
//                                          @RequestParam(required = false, defaultValue = "30") int size) {
//
//        List<PosDeviceResponseDto> devices;
//        if (online != null && provider != null) {
//            devices = posDeviceServicePort.getPosDevicesByStatusAndProvider(online, provider, pageNo, size);
//        } else if (online != null) {
//            devices = posDeviceServicePort.getPosDevicesByStatus(online, pageNo, size);
//        } else if (provider != null) {
//            devices = posDeviceServicePort.getPosDevicesByProvider(provider, pageNo, size);
//        } else {
//            devices = posDeviceServicePort.getAllPosDevices(pageNo, size);
//        }
//
//        return devices;
//    }
//
//    @GetMapping(path = "/{id}/statuslog", produces = MediaType.APPLICATION_JSON_VALUE)
//    List<PosDeviceStatusChangeLogDto> getStatusChangeLogsForDevice(@PathVariable("id") Long id, @RequestParam(required = false, defaultValue = "0") int pageNo,
//                                                                   @RequestParam(required = false, defaultValue = "30") int size) {
//        return posDeviceServicePort.getStatusChangeLogsForDevice(id, pageNo, size);
//    }

}
