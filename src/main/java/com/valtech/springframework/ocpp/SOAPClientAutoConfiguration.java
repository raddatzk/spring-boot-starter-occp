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

package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.config.client.ClientProfilesConfig;
import com.valtech.springframework.ocpp.config.client.ClientProperties;
import com.valtech.springframework.ocpp.config.client.ClientTools;
import eu.chargetime.ocpp.ClientEvents;
import eu.chargetime.ocpp.SOAPClient;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Configuration
@ConditionalOnExpression("${spring.ocpp.client.enabled:true} && '${spring.ocpp.client.type}'.equals('soap')")
@EnableConfigurationProperties({ClientProperties.class})
@Import({ClientProfilesConfig.class})
public class SOAPClientAutoConfiguration {

    private final ClientProperties clientProperties;

    private final ClientCoreProfile clientCoreProfile;
    private final Optional<ClientFirmwareManagementProfile> clientFirmwareManagementProfile;
    private final Optional<ClientLocalAuthListProfile> clientLocalAuthListProfile;
    private final Optional<ClientRemoteTriggerProfile> clientRemoteTriggerProfile;
    private final Optional<ClientReservationProfile> clientReservationProfile;
    private final Optional<ClientSmartChargingProfile> clientSmartChargingProfile;
    private final Optional<ClientEvents> clientEvents;

    @SuppressWarnings("squid:S00107")
    public SOAPClientAutoConfiguration(ClientProperties clientProperties, ClientCoreProfile clientCoreProfile, Optional<ClientFirmwareManagementProfile> clientFirmwareManagementProfile, Optional<ClientLocalAuthListProfile> clientLocalAuthListProfile, Optional<ClientRemoteTriggerProfile> clientRemoteTriggerProfile, Optional<ClientReservationProfile> clientReservationProfile, Optional<ClientSmartChargingProfile> clientSmartChargingProfile, Optional<ClientEvents> clientEvents) {
        this.clientProperties = clientProperties;
        this.clientCoreProfile = clientCoreProfile;
        this.clientFirmwareManagementProfile = clientFirmwareManagementProfile;
        this.clientLocalAuthListProfile = clientLocalAuthListProfile;
        this.clientRemoteTriggerProfile = clientRemoteTriggerProfile;
        this.clientReservationProfile = clientReservationProfile;
        this.clientSmartChargingProfile = clientSmartChargingProfile;
        this.clientEvents = clientEvents;
    }

    @Bean("soapClient")
    @ConditionalOnExpression("${spring.ocpp.server.enabled:true} and '${spring.ocpp.server.type}'.equals('soap')")
    @DependsOn("soapServer")
    SOAPClient soapClientWithServer() throws IOException {
        return createSoapClient();
    }

    @Bean
    SOAPClient soapClient() throws IOException {
        return createSoapClient();
    }

    private SOAPClient createSoapClient() throws IOException {
        ClientTools.validateClientProperties(clientProperties);

        SOAPClient client = new SOAPClient(clientProperties.getIdentifier(), new URL(clientProperties.getSoapCallback()), clientCoreProfile);

        clientFirmwareManagementProfile.ifPresent(client::addFeatureProfile);
        clientLocalAuthListProfile.ifPresent(client::addFeatureProfile);
        clientRemoteTriggerProfile.ifPresent(client::addFeatureProfile);
        clientReservationProfile.ifPresent(client::addFeatureProfile);
        clientSmartChargingProfile.ifPresent(client::addFeatureProfile);

        client.connect(clientProperties.getConnectionUrl(), clientEvents.orElse(null));

        return client;
    }
}
