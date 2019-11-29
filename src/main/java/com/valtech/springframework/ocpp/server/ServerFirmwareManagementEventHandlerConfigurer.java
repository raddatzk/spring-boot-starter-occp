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

    default DiagnosticsStatusNotificationConfirmation handleDiagnosticsStatusNotificationRequest(UUID uuid, DiagnosticsStatusNotificationRequest diagnosticsStatusNotificationRequest) {
        return null;
    }

    default FirmwareStatusNotificationConfirmation handleFirmwareStatusNotificationRequest(UUID uuid, FirmwareStatusNotificationRequest firmwareStatusNotificationRequest) {
        return null;
    }
}
