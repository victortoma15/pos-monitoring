package io.coremaker.internship.posmonitoring.domain.exception;

public class PosDeviceAlreadyExistsException extends RuntimeException {
    public PosDeviceAlreadyExistsException(String deviceId) {
        super("Device with id " + deviceId + " already exists for this provider!");
    }

}
