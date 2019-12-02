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

package com.valtech.springframework.ocpp.config.server;

import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.server.enabled", havingValue = "true")
public class ServerProfilesConfig {

    @Bean
    public ServerCoreProfile serverCoreProfile(ServerCoreEventHandler serverCoreEventHandler) {
        return new ServerCoreProfile(serverCoreEventHandler);
    }

    @Bean
    @ConditionalOnBean(ServerFirmwareManagementEventHandler.class)
    public ServerFirmwareManagementProfile serverFirmwareManagementProfile(ServerFirmwareManagementEventHandler serverFirmwareManagementEventHandler) {
        return new ServerFirmwareManagementProfile(serverFirmwareManagementEventHandler);
    }

    @Bean
    public ServerLocalAuthListProfile serverLocalAuthListProfile() {
        return new ServerLocalAuthListProfile();
    }

    @Bean
    public ServerRemoteTriggerProfile serverRemoteTriggerProfile() {
        return new ServerRemoteTriggerProfile();
    }

    @Bean
    public ServerSmartChargingProfile serverSmartChargingProfile() {
        return new ServerSmartChargingProfile();
    }

    @Bean
    public ServerReservationProfile serverReservationProfile() {
        return new ServerReservationProfile();
    }


}
