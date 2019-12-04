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

package com.valtech.springframework.boot.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientFirmwareManagementEventHandler;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsConfirmation;
import eu.chargetime.ocpp.model.firmware.GetDiagnosticsRequest;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareConfirmation;
import eu.chargetime.ocpp.model.firmware.UpdateFirmwareRequest;

public interface ClientFirmwareManagementEventHandlerAdapter extends ClientFirmwareManagementEventHandler {

    /**
     * Handle a {@link GetDiagnosticsRequest}
     *
     * @param getDiagnosticsRequest {@link GetDiagnosticsRequest}, the received request
     * @return {@link GetDiagnosticsConfirmation}, defaults to null (unsupported operation)
     */
    default GetDiagnosticsConfirmation handleGetDiagnosticsRequest(GetDiagnosticsRequest getDiagnosticsRequest) {
        return null;
    }

    /**
     * Handle a {@link UpdateFirmwareRequest}
     *
     * @param updateFirmwareRequest {@link UpdateFirmwareRequest}, the received request
     * @return {@link UpdateFirmwareConfirmation}, defaults to null (unsupported operation)
     */
    default UpdateFirmwareConfirmation handleUpdateFirmwareRequest(UpdateFirmwareRequest updateFirmwareRequest) {
        return null;
    }
}
