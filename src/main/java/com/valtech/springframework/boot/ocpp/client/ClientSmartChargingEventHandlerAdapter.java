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

import eu.chargetime.ocpp.feature.profile.ClientSmartChargingEventHandler;
import eu.chargetime.ocpp.model.smartcharging.*;

public interface ClientSmartChargingEventHandlerAdapter extends ClientSmartChargingEventHandler {

    /**
     * Handle a {@link SetChargingProfileRequest}
     *
     * @param setChargingProfileRequest {@link SetChargingProfileRequest}, the received request
     * @return {@link SetChargingProfileConfirmation}, defaults to null (unsupported operation)
     */
    default SetChargingProfileConfirmation handleSetChargingProfileRequest(SetChargingProfileRequest setChargingProfileRequest) {
        return null;
    }

    /**
     * Handle a {@link ClearChargingProfileRequest}
     *
     * @param clearChargingProfileRequest {@link ClearChargingProfileRequest}, the received request
     * @return {@link ClearChargingProfileConfirmation}, defaults to null (unsupported operation)
     */
    default ClearChargingProfileConfirmation handleClearChargingProfileRequest(ClearChargingProfileRequest clearChargingProfileRequest) {
        return null;
    }

    /**
     * Handle a {@link GetCompositeScheduleRequest}
     *
     * @param getCompositeScheduleRequest {@link GetCompositeScheduleRequest}, the received request
     * @return {@link GetCompositeScheduleConfirmation}, defaults to null (unsupported operation)
     */
    default GetCompositeScheduleConfirmation handleGetCompositeScheduleRequest(GetCompositeScheduleRequest getCompositeScheduleRequest) {
        return null;
    }
}
