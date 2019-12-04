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

import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.model.SessionInformation;
import eu.chargetime.ocpp.model.core.AuthorizeRequest;

import java.util.UUID;

public interface ServerEventsAdapter extends ServerEvents {

    /**
     * Handle a new Session
     *
     * @param sessionIndex       source of the request
     * @param sessionInformation {@link SessionInformation}, information about the incoming session
     */
    default void newSession(UUID sessionIndex, SessionInformation sessionInformation) {
    }

    /**
     * Handle a {@link AuthorizeRequest}
     *
     * @param sessionIndex source of the request
     */
    default void lostSession(UUID sessionIndex) {
    }
}
