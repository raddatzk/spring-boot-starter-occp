package com.valtech.springframework.ocpp.server;

import eu.chargetime.ocpp.feature.profile.ServerFirmwareManagementEventHandler;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationConfirmation;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationRequest;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationConfirmation;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnMissingBean(ServerFirmwareManagementEventHandler.class)
public interface ServerFirmwareManagementEventHandlerConfigurer extends ServerFirmwareManagementEventHandler {

    /**
     * Handle a ${@link DiagnosticsStatusNotificationRequest}
     *
     * @param sessionIndex                         source of the request
     * @param diagnosticsStatusNotificationRequest ${@link DiagnosticsStatusNotificationRequest}, the received request
     * @return confirmation ${@link DiagnosticsStatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default DiagnosticsStatusNotificationConfirmation handleDiagnosticsStatusNotificationRequest(UUID sessionIndex, DiagnosticsStatusNotificationRequest diagnosticsStatusNotificationRequest) {
        return null;
    }

    /**
     * Handle a ${@link FirmwareStatusNotificationRequest}
     *
     * @param sessionIndex                      source of the request
     * @param firmwareStatusNotificationRequest ${@link FirmwareStatusNotificationRequest}, the received request
     * @return confirmation ${@link FirmwareStatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default FirmwareStatusNotificationConfirmation handleFirmwareStatusNotificationRequest(UUID sessionIndex, FirmwareStatusNotificationRequest firmwareStatusNotificationRequest) {
        return null;
    }
}
