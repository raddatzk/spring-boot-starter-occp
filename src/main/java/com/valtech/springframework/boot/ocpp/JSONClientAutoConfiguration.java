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

import com.valtech.springframework.boot.ocpp.config.client.ClientProfilesConfig;
import com.valtech.springframework.boot.ocpp.config.client.ClientProperties;
import com.valtech.springframework.boot.ocpp.config.client.ClientSSLConfig;
import com.valtech.springframework.boot.ocpp.config.client.ClientTools;
import eu.chargetime.ocpp.ClientEvents;
import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Optional;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.client.enabled", havingValue = "true")
@EnableConfigurationProperties({ClientProperties.class})
@Import({ClientProfilesConfig.class, ClientSSLConfig.class})
public class JSONClientAutoConfiguration {

    private final ClientProperties clientProperties;

    private final ClientCoreProfile clientCoreProfile;
    private final Optional<ClientFirmwareManagementProfile> clientFirmwareManagementProfile;
    private final Optional<ClientLocalAuthListProfile> clientLocalAuthListProfile;
    private final Optional<ClientRemoteTriggerProfile> clientRemoteTriggerProfile;
    private final Optional<ClientReservationProfile> clientReservationProfile;
    private final Optional<ClientSmartChargingProfile> clientSmartChargingProfile;
    private final Optional<ClientEvents> clientEvents;
    private final Optional<SSLContext> sslContext;

    @java.lang.SuppressWarnings("squid:S00107")
    public JSONClientAutoConfiguration(ClientProperties clientProperties, ClientCoreProfile clientCoreProfile, Optional<ClientFirmwareManagementProfile> clientFirmwareManagementProfile, Optional<ClientLocalAuthListProfile> clientLocalAuthListProfile, Optional<ClientRemoteTriggerProfile> clientRemoteTriggerProfile, Optional<ClientReservationProfile> clientReservationProfile, Optional<ClientSmartChargingProfile> clientSmartChargingProfile, Optional<ClientEvents> clientEvents, @Qualifier("clientSSLContext") Optional<SSLContext> sslContext) {
        this.clientProperties = clientProperties;
        this.clientCoreProfile = clientCoreProfile;
        this.clientFirmwareManagementProfile = clientFirmwareManagementProfile;
        this.clientLocalAuthListProfile = clientLocalAuthListProfile;
        this.clientRemoteTriggerProfile = clientRemoteTriggerProfile;
        this.clientReservationProfile = clientReservationProfile;
        this.clientSmartChargingProfile = clientSmartChargingProfile;
        this.clientEvents = clientEvents;
        this.sslContext = sslContext;
    }

    @Bean("jsonClient")
    @ConditionalOnProperty(value = "spring.ocpp.server.enabled", havingValue = "true")
    @DependsOn("jsonServer")
    JSONClient jsonClientWithServer() throws IOException {
        return createJsonClient();
    }

    @Bean
    JSONClient jsonClient() throws IOException {
        return createJsonClient();
    }

    private JSONClient createJsonClient() throws IOException {
        ClientTools.validateClientProperties(clientProperties);

        JSONClient client = new JSONClient(clientCoreProfile, clientProperties.getIdentifier());

        clientFirmwareManagementProfile.ifPresent(client::addFeatureProfile);
        clientLocalAuthListProfile.ifPresent(client::addFeatureProfile);
        clientRemoteTriggerProfile.ifPresent(client::addFeatureProfile);
        clientReservationProfile.ifPresent(client::addFeatureProfile);
        clientSmartChargingProfile.ifPresent(client::addFeatureProfile);

        if (sslContext.isPresent()) {
            client.enableWSS(sslContext.get());
        }

        client.connect(clientProperties.getConnectionUrl(), clientEvents.orElse(null));

        return client;
    }
}
