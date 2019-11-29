package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientSmartChargingEventHandler;
import eu.chargetime.ocpp.model.smartcharging.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientSmartChargingEventHandler.class)
public interface ClientSmartChargingEventHandlerConfigurer extends ClientSmartChargingEventHandler {

    /**
     * Handle a ${@link SetChargingProfileRequest}
     *
     * @param setChargingProfileRequest ${@link SetChargingProfileRequest}, the received request
     * @return confirmation ${@link SetChargingProfileConfirmation}, defaults to null (unsupported operation)
     */
    default SetChargingProfileConfirmation handleSetChargingProfileRequest(SetChargingProfileRequest setChargingProfileRequest) {
        return null;
    }

    /**
     * Handle a ${@link ClearChargingProfileRequest}
     *
     * @param clearChargingProfileRequest ${@link ClearChargingProfileRequest}, the received request
     * @return confirmation ${@link ClearChargingProfileConfirmation}, defaults to null (unsupported operation)
     */
    default ClearChargingProfileConfirmation handleClearChargingProfileRequest(ClearChargingProfileRequest clearChargingProfileRequest) {
        return null;
    }

    /**
     * Handle a ${@link GetCompositeScheduleRequest}
     *
     * @param getCompositeScheduleRequest ${@link GetCompositeScheduleRequest}, the received request
     * @return confirmation ${@link GetCompositeScheduleConfirmation}, defaults to null (unsupported operation)
     */
    default GetCompositeScheduleConfirmation handleGetCompositeScheduleRequest(GetCompositeScheduleRequest getCompositeScheduleRequest) {
        return null;
    }
}
