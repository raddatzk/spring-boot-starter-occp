package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientReservationEventHandler;
import eu.chargetime.ocpp.model.reservation.CancelReservationConfirmation;
import eu.chargetime.ocpp.model.reservation.CancelReservationRequest;
import eu.chargetime.ocpp.model.reservation.ReserveNowConfirmation;
import eu.chargetime.ocpp.model.reservation.ReserveNowRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientReservationEventHandler.class)
public class AbstractClientReservationEventHandler implements ClientReservationEventHandler {

    public ReserveNowConfirmation handleReserveNowRequest(ReserveNowRequest reserveNowRequest) {
        return null;
    }

    public CancelReservationConfirmation handleCancelReservationRequest(CancelReservationRequest cancelReservationRequest) {
        return null;
    }
}
