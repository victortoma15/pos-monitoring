package io.coremaker.internship.posmonitoring.controllers;

public class PosNotFoundException extends RuntimeException{
    public PosNotFoundException(Long id) {
        super("Couldn't find pos " + id);
    }
}
