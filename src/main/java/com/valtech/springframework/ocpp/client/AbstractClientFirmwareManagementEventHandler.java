package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientFirmwareManagementEventHandler;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsConfirmation;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsRequest;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareConfirmation;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientFirmwareManagementEventHandler.class)
public class AbstractClientFirmwareManagementEventHandler implements ClientFirmwareManagementEventHandler{

    public GetDiagnosticsConfirmation handleGetDiagnosticsRequest(GetDiagnosticsRequest getDiagnosticsRequest) {
        return null;
    }

    public UpdateFirmwareConfirmation handleUpdateFirmwareRequest(UpdateFirmwareRequest updateFirmwareRequest) {
        return null;
    }
}
