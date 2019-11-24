package com.valtech.springframework.ocpp.server;

import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.model.SessionInformation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnMissingBean(ServerEvents.class)
public class AbstractServerEvents implements ServerEvents {

    public void newSession(UUID uuid, SessionInformation sessionInformation) {

    }

    public void lostSession(UUID uuid) {

    }
}
