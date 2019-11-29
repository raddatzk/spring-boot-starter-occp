package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClientCoreEventHandler.class)
public interface ClientCoreEventHandlerConfigurer extends ClientCoreEventHandler {

    /**
     * Handle a ${@link ChangeAvailabilityRequest}
     *
     * @param changeAvailabilityRequest ${@link ChangeAvailabilityRequest}, the received request
     * @return confirmation ${@link ChangeAvailabilityConfirmation}, defaults to null (unsupported operation)
     */
    default ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest changeAvailabilityRequest) {
        return null;
    }

    /**
     * Handle a ${@link GetConfigurationRequest}
     *
     * @param getConfigurationRequest ${@link GetConfigurationRequest}, the received request
     * @return confirmation ${@link GetConfigurationConfirmation}, defaults to null (unsupported operation)
     */
    default GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest getConfigurationRequest) {
        return null;
    }

    /**
     * Handle a ${@link ChangeConfigurationRequest}
     *
     * @param changeConfigurationRequest ${@link ChangeConfigurationRequest}, the received request
     * @return confirmation ${@link ChangeConfigurationConfirmation}, defaults to null (unsupported operation)
     */
    default ChangeConfigurationConfirmation handleChangeConfigurationRequest(ChangeConfigurationRequest changeConfigurationRequest) {
        return null;
    }

    /**
     * Handle a ${@link ClearCacheRequest}
     *
     * @param clearCacheRequest ${@link ClearCacheRequest}, the received request
     * @return confirmation ${@link ClearCacheConfirmation}, defaults to null (unsupported operation)
     */
    default ClearCacheConfirmation handleClearCacheRequest(ClearCacheRequest clearCacheRequest) {
        return null;
    }

    /**
     * Handle a ${@link DataTransferRequest}
     *
     * @param dataTransferRequest ${@link DataTransferRequest}, the received request
     * @return confirmation ${@link DataTransferConfirmation}, defaults to null (unsupported operation)
     */
    default DataTransferConfirmation handleDataTransferRequest(DataTransferRequest dataTransferRequest) {
        return null;
    }

    /**
     * Handle a ${@link RemoteStartTransactionRequest}
     *
     * @param remoteStartTransactionRequest ${@link RemoteStartTransactionRequest}, the received request
     * @return confirmation ${@link RemoteStartTransactionConfirmation}, defaults to null (unsupported operation)
     */
    default RemoteStartTransactionConfirmation handleRemoteStartTransactionRequest(RemoteStartTransactionRequest remoteStartTransactionRequest) {
        return null;
    }

    /**
     * Handle a ${@link RemoteStopTransactionRequest}
     *
     * @param remoteStopTransactionRequest ${@link RemoteStopTransactionRequest}, the received request
     * @return confirmation ${@link RemoteStopTransactionConfirmation}, defaults to null (unsupported operation)
     */
    default RemoteStopTransactionConfirmation handleRemoteStopTransactionRequest(RemoteStopTransactionRequest remoteStopTransactionRequest) {
        return null;
    }

    /**
     * Handle a ${@link ResetRequest}
     *
     * @param resetRequest ${@link ResetRequest}, the received request
     * @return confirmation ${@link ResetConfirmation}, defaults to null (unsupported operation)
     */
    default ResetConfirmation handleResetRequest(ResetRequest resetRequest) {
        return null;
    }

    /**
     * Handle a ${@link UnlockConnectorRequest}
     *
     * @param unlockConnectorRequest ${@link UnlockConnectorRequest}, the received request
     * @return confirmation ${@link UnlockConnectorConfirmation}, defaults to null (unsupported operation)
     */
    default UnlockConnectorConfirmation handleUnlockConnectorRequest(UnlockConnectorRequest unlockConnectorRequest) {
        return null;
    }
}
