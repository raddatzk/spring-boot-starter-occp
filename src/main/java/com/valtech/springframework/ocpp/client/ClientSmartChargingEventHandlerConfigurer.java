package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientSmartChargingEventHandler;
import eu.chargetime.ocpp.model.smartcharging.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientSmartChargingEventHandler.class)
public interface ClientSmartChargingEventHandlerConfigurer extends ClientSmartChargingEventHandler {

    default SetChargingProfileConfirmation handleSetChargingProfileRequest(SetChargingProfileRequest setChargingProfileRequest) {
        return null;
    }

    default ClearChargingProfileConfirmation handleClearChargingProfileRequest(ClearChargingProfileRequest clearChargingProfileRequest) {
        return null;
    }

    default GetCompositeScheduleConfirmation handleGetCompositeScheduleRequest(GetCompositeScheduleRequest getCompositeScheduleRequest) {
        return null;
    }
}
