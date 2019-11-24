package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientSmartChargingEventHandler;
import eu.chargetime.ocpp.model.smartcharging.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientSmartChargingEventHandler.class)
public class AbstractClientSmartChargingEventHandler implements ClientSmartChargingEventHandler {

    public SetChargingProfileConfirmation handleSetChargingProfileRequest(SetChargingProfileRequest setChargingProfileRequest) {
        return null;
    }

    public ClearChargingProfileConfirmation handleClearChargingProfileRequest(ClearChargingProfileRequest clearChargingProfileRequest) {
        return null;
    }

    public GetCompositeScheduleConfirmation handleGetCompositeScheduleRequest(GetCompositeScheduleRequest getCompositeScheduleRequest) {
        return null;
    }
}
