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

package com.valtech.springframework.boot.ocpp.config.client;

import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.client.enabled", havingValue = "true")
public class ClientProfilesConfig {

    @Bean
    public ClientCoreProfile clientCoreProfile(ClientCoreEventHandler clientCoreEventHandler) {
        return new ClientCoreProfile(clientCoreEventHandler);
    }

    @Bean
    @ConditionalOnBean(ClientFirmwareManagementEventHandler.class)
    public ClientFirmwareManagementProfile clientFirmwareManagementProfile(ClientFirmwareManagementEventHandler clientFirmwareManagementEventHandler) {
        return new ClientFirmwareManagementProfile(clientFirmwareManagementEventHandler);
    }

    @Bean
    @ConditionalOnBean(ClientLocalAuthListEventHandler.class)
    public ClientLocalAuthListProfile clientLocalAuthListProfile(ClientLocalAuthListEventHandler clientLocalAuthListEventHandler) {
        return new ClientLocalAuthListProfile(clientLocalAuthListEventHandler);
    }

    @Bean
    @ConditionalOnBean(ClientRemoteTriggerEventHandler.class)
    public ClientRemoteTriggerProfile clientRemoteTriggerProfile(ClientRemoteTriggerEventHandler clientRemoteTriggerEventHandler) {
        return new ClientRemoteTriggerProfile(clientRemoteTriggerEventHandler);
    }

    @Bean
    @ConditionalOnBean(ClientReservationEventHandler.class)
    public ClientReservationProfile clientReservationProfile(ClientReservationEventHandler clientReservationEventHandler) {
        return new ClientReservationProfile(clientReservationEventHandler);
    }

    @Bean
    @ConditionalOnBean(ClientSmartChargingEventHandler.class)
    public ClientSmartChargingProfile clientSmartChargingProfile(ClientSmartChargingEventHandler clientSmartChargingEventHandler) {
        return new ClientSmartChargingProfile(clientSmartChargingEventHandler);
    }
}
