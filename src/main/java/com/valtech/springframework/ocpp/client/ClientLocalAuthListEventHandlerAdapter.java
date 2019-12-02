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

package com.valtech.springframework.ocpp.client;

import eu.chargetime.ocpp.feature.profile.ClientLocalAuthListEventHandler;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionConfirmation;
import eu.chargetime.ocpp.model.localauthlist.GetLocalListVersionRequest;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListConfirmation;
import eu.chargetime.ocpp.model.localauthlist.SendLocalListRequest;

public interface ClientLocalAuthListEventHandlerAdapter extends ClientLocalAuthListEventHandler {

    /**
     * Handle a {@link GetLocalListVersionRequest}
     *
     * @param getLocalListVersionRequest {@link GetLocalListVersionRequest}, the received request
     * @return {@link GetLocalListVersionConfirmation}, defaults to null (unsupported operation)
     */
    default GetLocalListVersionConfirmation handleGetLocalListVersionRequest(GetLocalListVersionRequest getLocalListVersionRequest) {
        return null;
    }

    /**
     * Handle a {@link SendLocalListRequest}
     *
     * @param sendLocalListRequest {@link SendLocalListRequest}, the received request
     * @return {@link SendLocalListConfirmation}, defaults to null (unsupported operation)
     */
    default SendLocalListConfirmation handleSendLocalListRequest(SendLocalListRequest sendLocalListRequest) {
        return null;
    }
}
