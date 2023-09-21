package io.coremaker.internship.posmonitoring.application.grpc.posdevice;

import io.coremaker.internship.posmonitoring.*;
import io.coremaker.internship.posmonitoring.domain.model.PosDevice;
import io.coremaker.internship.posmonitoring.domain.model.command.CreatePosDeviceCommand;
import io.coremaker.internship.posmonitoring.domain.port.PosDeviceServicePort;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.time.Instant;

@GrpcService
public class PosDeviceGrpcController extends PosDeviceServiceGrpc.PosDeviceServiceImplBase {
    private final PosDeviceServicePort posDeviceServicePort;

    public PosDeviceGrpcController(PosDeviceServicePort posDeviceServicePort) {
        this.posDeviceServicePort = posDeviceServicePort;
    }

    @Override
    public void addPosDevice(CreatePosDeviceRequest.CreatePosDeviceRequestGrpc request, StreamObserver<CreatePosDeviceResponse.CreatePosDeviceResponseGrpc> responseObserver) {
        CreatePosDeviceCommand command = new CreatePosDeviceCommand();
        command.setDeviceId(request.getDeviceId());
        command.setLocation(request.getLocation());
        command.setProvider(request.getProvider());
        command.setOnline(request.getOnline());
        command.setLastActivity(Instant.parse(request.getLastActivity()));

        PosDevice posDevice = posDeviceServicePort.create(command);

        CreatePosDeviceResponse.CreatePosDeviceResponseGrpc response = CreatePosDeviceResponse.CreatePosDeviceResponseGrpc.newBuilder()
                .setId(posDevice.getId())
                .setDeviceId(posDevice.getDeviceId())
                .setLocation(posDevice.getLocation())
                .setProvider(posDevice.getProvider())
                .setOnline(posDevice.getOnline())
                .setLastActivity(posDevice.getLastActivity().toString())
                .setCreatedAt(posDevice.getCreatedAt().toString())
                .setUpdatedAt(posDevice.getUpdatedAt().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getPosDevice(GetPosDeviceDetailsRequest.GetDeviceDetailsRequestGrpc request, StreamObserver<GetPosDeviceDetailsResponse.GetDeviceDetailsResponseGrpc> responseObserver) {
        long id = request.getId();

        PosDevice posDevice = posDeviceServicePort.getById(id);

        if (posDevice != null) {
            GetPosDeviceDetailsResponse.GetDeviceDetailsResponseGrpc responseGrpc = GetPosDeviceDetailsResponse.GetDeviceDetailsResponseGrpc.newBuilder()
                    .setId(posDevice.getId())
                    .setDeviceId(posDevice.getDeviceId())
                    .setLocation(posDevice.getLocation())
                    .setProvider(posDevice.getProvider())
                    .setOnline(posDevice.getOnline())
                    .setLastActivity(posDevice.getLastActivity().toString())
                    .setCreatedAt(posDevice.getCreatedAt().toString())
                    .setUpdatedAt(posDevice.getUpdatedAt().toString())
                    .build();

            responseObserver.onNext(responseGrpc);
            responseObserver.onCompleted();
        }
    }


}
