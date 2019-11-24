package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientRemoteTriggerHandler;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageConfirmation;
import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientRemoteTriggerHandler.class)
public class AbstractClientRemoteTriggerEventHandler implements ClientRemoteTriggerHandler {

    public TriggerMessageConfirmation handleTriggerMessageRequest(TriggerMessageRequest triggerMessageRequest) {
        return null;
    }
}
