package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientSmartChargingEventHandler;
import eu.chargetime.ocpp.model.smartcharging.*;

public interface ClientSmartChargingEventHandlerAdapter extends ClientSmartChargingEventHandler {

    /**
     * Handle a {@link SetChargingProfileRequest}
     *
     * @param setChargingProfileRequest {@link SetChargingProfileRequest}, the received request
     * @return {@link SetChargingProfileConfirmation}, defaults to null (unsupported operation)
     */
    default SetChargingProfileConfirmation handleSetChargingProfileRequest(SetChargingProfileRequest setChargingProfileRequest) {
        return null;
    }

    /**
     * Handle a {@link ClearChargingProfileRequest}
     *
     * @param clearChargingProfileRequest {@link ClearChargingProfileRequest}, the received request
     * @return {@link ClearChargingProfileConfirmation}, defaults to null (unsupported operation)
     */
    default ClearChargingProfileConfirmation handleClearChargingProfileRequest(ClearChargingProfileRequest clearChargingProfileRequest) {
        return null;
    }

    /**
     * Handle a {@link GetCompositeScheduleRequest}
     *
     * @param getCompositeScheduleRequest {@link GetCompositeScheduleRequest}, the received request
     * @return {@link GetCompositeScheduleConfirmation}, defaults to null (unsupported operation)
     */
    default GetCompositeScheduleConfirmation handleGetCompositeScheduleRequest(GetCompositeScheduleRequest getCompositeScheduleRequest) {
        return null;
    }
}
