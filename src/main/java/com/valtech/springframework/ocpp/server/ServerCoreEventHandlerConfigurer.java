package com.valtech.springframework.ocpp.server;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConditionalOnMissingBean(ServerCoreEventHandler.class)
public interface ServerCoreEventHandlerConfigurer extends ServerCoreEventHandler {

    /**
     * Handle a ${@link AuthorizeRequest}
     *
     * @param sessionIndex     source of the request
     * @param authorizeRequest ${@link AuthorizeRequest}, the received request
     * @return confirmation ${@link AuthorizeConfirmation}, defaults to null (unsupported operation)
     */
    default AuthorizeConfirmation handleAuthorizeRequest(UUID sessionIndex, AuthorizeRequest authorizeRequest) {
        return null;
    }

    /**
     * Handle a ${@link BootNotificationRequest}
     *
     * @param sessionIndex            source of the request
     * @param bootNotificationRequest ${@link BootNotificationRequest}, the received request
     * @return confirmation ${@link BootNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default BootNotificationConfirmation handleBootNotificationRequest(UUID sessionIndex, BootNotificationRequest bootNotificationRequest) {
        return null;
    }

    /**
     * Handle a ${@link DataTransferRequest}
     *
     * @param sessionIndex        source of the request
     * @param dataTransferRequest ${@link DataTransferRequest}, the received request
     * @return confirmation ${@link DataTransferConfirmation}, defaults to null (unsupported operation)
     */
    default DataTransferConfirmation handleDataTransferRequest(UUID sessionIndex, DataTransferRequest dataTransferRequest) {
        return null;
    }

    /**
     * Handle a ${@link HeartbeatRequest}
     *
     * @param sessionIndex     source of the request
     * @param heartbeatRequest ${@link HeartbeatRequest}, the received request
     * @return confirmation ${@link HeartbeatConfirmation}, defaults to null (unsupported operation)
     */
    default HeartbeatConfirmation handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest heartbeatRequest) {
        return null;
    }

    /**
     * Handle a ${@link MeterValuesRequest}
     *
     * @param sessionIndex       source of the request
     * @param meterValuesRequest ${@link MeterValuesRequest}, the received request
     * @return confirmation ${@link MeterValuesConfirmation}, defaults to null (unsupported operation)
     */
    default MeterValuesConfirmation handleMeterValuesRequest(UUID sessionIndex, MeterValuesRequest meterValuesRequest) {
        return null;
    }

    /**
     * Handle a ${@link StartTransactionRequest}
     *
     * @param sessionIndex            source of the request
     * @param startTransactionRequest ${@link StartTransactionRequest}, the received request
     * @return confirmation ${@link StartTransactionConfirmation}, defaults to null (unsupported operation)
     */
    default StartTransactionConfirmation handleStartTransactionRequest(UUID sessionIndex, StartTransactionRequest startTransactionRequest) {
        return null;
    }

    /**
     * Handle a ${@link StatusNotificationRequest}
     *
     * @param sessionIndex              source of the request
     * @param statusNotificationRequest ${@link StatusNotificationRequest}, the received request
     * @return confirmation ${@link StatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default StatusNotificationConfirmation handleStatusNotificationRequest(UUID sessionIndex, StatusNotificationRequest statusNotificationRequest) {
        return null;
    }

    /**
     * Handle a ${@link StopTransactionRequest}
     *
     * @param sessionIndex           source of the request
     * @param stopTransactionRequest ${@link StopTransactionRequest}, the received request
     * @return confirmation ${@link StopTransactionConfirmation}, defaults to null (unsupported operation)
     */
    default StopTransactionConfirmation handleStopTransactionRequest(UUID sessionIndex, StopTransactionRequest stopTransactionRequest) {
        return null;
    }
}
