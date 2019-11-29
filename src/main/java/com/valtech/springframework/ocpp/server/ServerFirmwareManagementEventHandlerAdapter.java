package com.valtech.springframework.ocpp.server;

import eu.chargetime.ocpp.feature.profile.ServerFirmwareManagementEventHandler;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationConfirmation;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationRequest;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationConfirmation;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationRequest;

import java.util.UUID;

public interface ServerFirmwareManagementEventHandlerAdapter extends ServerFirmwareManagementEventHandler {

    /**
     * Handle a {@link DiagnosticsStatusNotificationRequest}
     *
     * @param sessionIndex                         source of the request
     * @param diagnosticsStatusNotificationRequest {@link DiagnosticsStatusNotificationRequest}, the received request
     * @return {@link DiagnosticsStatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default DiagnosticsStatusNotificationConfirmation handleDiagnosticsStatusNotificationRequest(UUID sessionIndex, DiagnosticsStatusNotificationRequest diagnosticsStatusNotificationRequest) {
        return null;
    }

    /**
     * Handle a {@link FirmwareStatusNotificationRequest}
     *
     * @param sessionIndex                      source of the request
     * @param firmwareStatusNotificationRequest {@link FirmwareStatusNotificationRequest}, the received request
     * @return {@link FirmwareStatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default FirmwareStatusNotificationConfirmation handleFirmwareStatusNotificationRequest(UUID sessionIndex, FirmwareStatusNotificationRequest firmwareStatusNotificationRequest) {
        return null;
    }
}
