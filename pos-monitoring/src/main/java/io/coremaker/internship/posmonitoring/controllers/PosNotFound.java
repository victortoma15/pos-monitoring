package io.coremaker.internship.posmonitoring.controllers;

public class PosNotFound extends RuntimeException{
    public PosNotFound(Long id) {
        super("Couldn't find pos " + id);
    }
}
