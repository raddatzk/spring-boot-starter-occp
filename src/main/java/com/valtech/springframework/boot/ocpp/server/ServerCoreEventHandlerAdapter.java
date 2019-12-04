/*
 *
 *  * Copyright 2019 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.valtech.springframework.boot.ocpp.server;

import eu.chargetime.ocpp.feature.profile.ServerCoreEventHandler;
import eu.chargetime.ocpp.model.core.*;

import java.util.UUID;

public interface ServerCoreEventHandlerAdapter extends ServerCoreEventHandler {

    /**
     * Handle a {@link AuthorizeRequest}
     *
     * @param sessionIndex     source of the request
     * @param authorizeRequest {@link AuthorizeRequest}, the received request
     * @return {@link AuthorizeConfirmation}, defaults to null (unsupported operation)
     */
    default AuthorizeConfirmation handleAuthorizeRequest(UUID sessionIndex, AuthorizeRequest authorizeRequest) {
        return null;
    }

    /**
     * Handle a {@link BootNotificationRequest}
     *
     * @param sessionIndex            source of the request
     * @param bootNotificationRequest {@link BootNotificationRequest}, the received request
     * @return {@link BootNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default BootNotificationConfirmation handleBootNotificationRequest(UUID sessionIndex, BootNotificationRequest bootNotificationRequest) {
        return null;
    }

    /**
     * Handle a {@link DataTransferRequest}
     *
     * @param sessionIndex        source of the request
     * @param dataTransferRequest {@link DataTransferRequest}, the received request
     * @return {@link DataTransferConfirmation}, defaults to null (unsupported operation)
     */
    default DataTransferConfirmation handleDataTransferRequest(UUID sessionIndex, DataTransferRequest dataTransferRequest) {
        return null;
    }

    /**
     * Handle a {@link HeartbeatRequest}
     *
     * @param sessionIndex     source of the request
     * @param heartbeatRequest {@link HeartbeatRequest}, the received request
     * @return {@link HeartbeatConfirmation}, defaults to null (unsupported operation)
     */
    default HeartbeatConfirmation handleHeartbeatRequest(UUID sessionIndex, HeartbeatRequest heartbeatRequest) {
        return null;
    }

    /**
     * Handle a {@link MeterValuesRequest}
     *
     * @param sessionIndex       source of the request
     * @param meterValuesRequest {@link MeterValuesRequest}, the received request
     * @return {@link MeterValuesConfirmation}, defaults to null (unsupported operation)
     */
    default MeterValuesConfirmation handleMeterValuesRequest(UUID sessionIndex, MeterValuesRequest meterValuesRequest) {
        return null;
    }

    /**
     * Handle a {@link StartTransactionRequest}
     *
     * @param sessionIndex            source of the request
     * @param startTransactionRequest {@link StartTransactionRequest}, the received request
     * @return {@link StartTransactionConfirmation}, defaults to null (unsupported operation)
     */
    default StartTransactionConfirmation handleStartTransactionRequest(UUID sessionIndex, StartTransactionRequest startTransactionRequest) {
        return null;
    }

    /**
     * Handle a {@link StatusNotificationRequest}
     *
     * @param sessionIndex              source of the request
     * @param statusNotificationRequest {@link StatusNotificationRequest}, the received request
     * @return {@link StatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default StatusNotificationConfirmation handleStatusNotificationRequest(UUID sessionIndex, StatusNotificationRequest statusNotificationRequest) {
        return null;
    }

    /**
     * Handle a {@link StopTransactionRequest}
     *
     * @param sessionIndex           source of the request
     * @param stopTransactionRequest {@link StopTransactionRequest}, the received request
     * @return {@link StopTransactionConfirmation}, defaults to null (unsupported operation)
     */
    default StopTransactionConfirmation handleStopTransactionRequest(UUID sessionIndex, StopTransactionRequest stopTransactionRequest) {
        return null;
    }
}
