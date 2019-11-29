package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.ClientEvents;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientEvents.class)
public interface ClientEventsConfigurer extends ClientEvents {

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
