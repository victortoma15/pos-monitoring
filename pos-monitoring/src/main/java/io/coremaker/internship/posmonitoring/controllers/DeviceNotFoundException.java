package io.coremaker.internship.posmonitoring.controllers;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(Long id) {
        super("Couldn't delete the device with ID " + id + ". It doesn't exist!");
    }
}