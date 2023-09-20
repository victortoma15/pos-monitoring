package io.coremaker.internship.posmonitoring.application.grpc.posdevice;

import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceAlreadyExistsException;
import io.coremaker.internship.posmonitoring.domain.exception.PosDeviceNotFoundException;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
@Slf4j
public class GlobalExceptionHandlerGrpc {
    @GrpcExceptionHandler(Exception.class)
    public Status handleException(Exception e) {
        log.error("Internal server error!", e);
        return Status.INTERNAL.withDescription("Something went wrong!");
    }

    @GrpcExceptionHandler(PosDeviceNotFoundException.class)
    public Status handleDeviceNotFoundException(PosDeviceNotFoundException e) {
        return Status.NOT_FOUND.withDescription(e.getMessage());
    }

    @GrpcExceptionHandler(PosDeviceAlreadyExistsException.class)
    public Status handleDeviceAlreadyExistsException(PosDeviceAlreadyExistsException e) {
        return Status.ALREADY_EXISTS.withDescription(e.getMessage());
    }

}
