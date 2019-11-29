package com.valtech.springframework.ocpp.server;

import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.model.SessionInformation;
import eu.chargetime.ocpp.model.core.AuthorizeRequest;

import java.util.UUID;

public interface ServerEventsAdapter extends ServerEvents {

    /**
     * Handle a new Session
     *
     * @param sessionIndex       source of the request
     * @param sessionInformation {@link SessionInformation}, information about the incoming session
     */
    default void newSession(UUID sessionIndex, SessionInformation sessionInformation) {
    }

    /**
     * Handle a {@link AuthorizeRequest}
     *
     * @param sessionIndex source of the request
     */
    default void lostSession(UUID sessionIndex) {
    }
}
