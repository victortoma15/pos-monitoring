package io.coremaker.internship.posmonitoring.domain.exception;

public class PosDeviceNotFoundException extends RuntimeException {
    public PosDeviceNotFoundException(Long id) {
        super("Couldn't find device with ID " + id + ". It doesn't exist!");
    }
}
