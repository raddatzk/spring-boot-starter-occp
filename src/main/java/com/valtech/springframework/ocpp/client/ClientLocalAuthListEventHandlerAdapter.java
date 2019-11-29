package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientLocalAuthListEventHandler;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionConfirmation;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListConfirmation;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;

public interface ClientLocalAuthListEventHandlerAdapter extends ClientLocalAuthListEventHandler {

    /**
     * Handle a {@link GetLocalListVersionRequest}
     *
     * @param getLocalListVersionRequest {@link GetLocalListVersionRequest}, the received request
     * @return {@link GetLocalListVersionConfirmation}, defaults to null (unsupported operation)
     */
    default GetLocalListVersionConfirmation handleGetLocalListVersionRequest(GetLocalListVersionRequest getLocalListVersionRequest) {
        return null;
    }

    /**
     * Handle a {@link SendLocalListRequest}
     *
     * @param sendLocalListRequest {@link SendLocalListRequest}, the received request
     * @return {@link SendLocalListConfirmation}, defaults to null (unsupported operation)
     */
    default SendLocalListConfirmation handleSendLocalListRequest(SendLocalListRequest sendLocalListRequest) {
        return null;
    }
}
