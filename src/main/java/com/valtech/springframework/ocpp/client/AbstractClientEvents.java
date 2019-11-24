package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.ClientEvents;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientEvents.class)
public class AbstractClientEvents implements ClientEvents {
    @Override
    public void connectionOpened() {

    }

    @Override
    public void connectionClosed() {

    }
}