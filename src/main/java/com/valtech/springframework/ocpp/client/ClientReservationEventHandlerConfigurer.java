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
public interface ClientReservationEventHandlerConfigurer extends ClientReservationEventHandler {

    /**
     * Handle a ${@link ReserveNowRequest}
     *
     * @param reserveNowRequest ${@link ReserveNowRequest}, the received request
     * @return confirmation ${@link ReserveNowConfirmation}, defaults to null (unsupported operation)
     */
    default ReserveNowConfirmation handleReserveNowRequest(ReserveNowRequest reserveNowRequest) {
        return null;
    }

    /**
     * Handle a ${@link CancelReservationRequest}
     *
     * @param cancelReservationRequest ${@link CancelReservationRequest}, the received request
     * @return confirmation ${@link CancelReservationConfirmation}, defaults to null (unsupported operation)
     */
    default CancelReservationConfirmation handleCancelReservationRequest(CancelReservationRequest cancelReservationRequest) {
        return null;
    }
}
