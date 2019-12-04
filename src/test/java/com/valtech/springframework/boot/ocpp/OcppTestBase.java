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

package com.valtech.springframework.boot.ocpp;

import com.valtech.springframework.boot.ocpp.client.*;
import com.valtech.springframework.boot.ocpp.server.ServerCoreEventHandlerAdapter;
import com.valtech.springframework.boot.ocpp.server.ServerEventsAdapter;
import com.valtech.springframework.boot.ocpp.server.ServerFirmwareManagementEventHandlerAdapter;

abstract class OcppTestBase {

    static class ClientCoreEventHandler implements ClientCoreEventHandlerAdapter {
    }

    static class ClientFirmwareManagementEventHandler implements ClientFirmwareManagementEventHandlerAdapter {
    }

    static class ClientLocalAuthListEventHandler implements ClientLocalAuthListEventHandlerAdapter {

    }

    static class ClientRemoteTriggerEventHandler implements ClientRemoteTriggerEventHandlerAdapter {

    }

    static class ClientReservationEventHandler implements ClientReservationEventHandlerAdapter {

    }

    static class ClientSmartChargingEventHandler implements ClientSmartChargingEventHandlerAdapter {

    }

    static class ClientEvents implements ClientEventsAdapter {
    }

    static class ServerCoreEventHandler implements ServerCoreEventHandlerAdapter {
    }

    static class ServerEvents implements ServerEventsAdapter {
    }

    static class ServerFirmwareManagementEventHandler implements ServerFirmwareManagementEventHandlerAdapter {
    }
}
