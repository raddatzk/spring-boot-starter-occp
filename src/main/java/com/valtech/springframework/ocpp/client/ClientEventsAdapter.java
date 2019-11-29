package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.ClientEvents;

public interface ClientEventsAdapter extends ClientEvents {

    /**
     * Handle an opened connection
     */
    default void connectionOpened() {
    }

    /**
     * Handle a closed connection
     */
    default void connectionClosed() {
    }
}
