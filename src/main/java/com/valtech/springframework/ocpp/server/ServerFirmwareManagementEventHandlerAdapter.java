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

package com.valtech.springframework.ocpp.server;

import eu.chargetime.ocpp.feature.profile.ServerFirmwareManagementEventHandler;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationConfirmation;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationRequest;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationConfirmation;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationRequest;

import java.util.UUID;

public interface ServerFirmwareManagementEventHandlerAdapter extends ServerFirmwareManagementEventHandler {

    /**
     * Handle a {@link DiagnosticsStatusNotificationRequest}
     *
     * @param sessionIndex                         source of the request
     * @param diagnosticsStatusNotificationRequest {@link DiagnosticsStatusNotificationRequest}, the received request
     * @return {@link DiagnosticsStatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default DiagnosticsStatusNotificationConfirmation handleDiagnosticsStatusNotificationRequest(UUID sessionIndex, DiagnosticsStatusNotificationRequest diagnosticsStatusNotificationRequest) {
        return null;
    }

    /**
     * Handle a {@link FirmwareStatusNotificationRequest}
     *
     * @param sessionIndex                      source of the request
     * @param firmwareStatusNotificationRequest {@link FirmwareStatusNotificationRequest}, the received request
     * @return {@link FirmwareStatusNotificationConfirmation}, defaults to null (unsupported operation)
     */
    default FirmwareStatusNotificationConfirmation handleFirmwareStatusNotificationRequest(UUID sessionIndex, FirmwareStatusNotificationRequest firmwareStatusNotificationRequest) {
        return null;
    }
}
