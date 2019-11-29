package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientLocalAuthListEventHandler;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionConfirmation;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListConfirmation;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientLocalAuthListEventHandler.class)
public interface ClientLocalAuthListEventHandlerConfigurer extends ClientLocalAuthListEventHandler {

    /**
     * Handle a ${@link GetLocalListVersionRequest}
     *
     * @param getLocalListVersionRequest ${@link GetLocalListVersionRequest}, the received request
     * @return confirmation ${@link GetLocalListVersionConfirmation}, defaults to null (unsupported operation)
     */
    default GetLocalListVersionConfirmation handleGetLocalListVersionRequest(GetLocalListVersionRequest getLocalListVersionRequest) {
        return null;
    }

    /**
     * Handle a ${@link SendLocalListRequest}
     *
     * @param sendLocalListRequest ${@link SendLocalListRequest}, the received request
     * @return confirmation ${@link SendLocalListConfirmation}, defaults to null (unsupported operation)
     */
    default SendLocalListConfirmation handleSendLocalListRequest(SendLocalListRequest sendLocalListRequest) {
        return null;
    }
}
