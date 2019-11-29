package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientFirmwareManagementEventHandler;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsConfirmation;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsRequest;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareConfirmation;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareRequest;

public interface ClientFirmwareManagementEventHandlerAdapter extends ClientFirmwareManagementEventHandler {

    /**
     * Handle a {@link GetDiagnosticsRequest}
     *
     * @param getDiagnosticsRequest {@link GetDiagnosticsRequest}, the received request
     * @return {@link GetDiagnosticsConfirmation}, defaults to null (unsupported operation)
     */
    default GetDiagnosticsConfirmation handleGetDiagnosticsRequest(GetDiagnosticsRequest getDiagnosticsRequest) {
        return null;
    }

    /**
     * Handle a {@link UpdateFirmwareRequest}
     *
     * @param updateFirmwareRequest {@link UpdateFirmwareRequest}, the received request
     * @return {@link UpdateFirmwareConfirmation}, defaults to null (unsupported operation)
     */
    default UpdateFirmwareConfirmation handleUpdateFirmwareRequest(UpdateFirmwareRequest updateFirmwareRequest) {
        return null;
    }
}
