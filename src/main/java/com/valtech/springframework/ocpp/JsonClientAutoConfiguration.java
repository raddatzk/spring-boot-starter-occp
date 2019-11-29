package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.client.ClientEventsConfigurer;
import com.valtech.springframework.ocpp.config.ClientProfilesConfig;
import com.valtech.springframework.ocpp.config.ClientProperties;
import eu.chargetime.ocpp.ClientEvents;
import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.util.Objects;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.client.enabled", havingValue = "true")
@EnableConfigurationProperties({ClientProperties.class})
@Import({ClientProfilesConfig.class, ClientEventsConfigurer.class})
public class JsonClientAutoConfiguration {

    @Autowired private ClientProperties clientProperties;

    private void validateClientProperties() {
        if (clientProperties.getEnabled()) {
            if (Objects.isNull(clientProperties.getConnectionUrl()) || "".equals(clientProperties.getConnectionUrl())) {
                throw new NullPointerException("If client is enabled, connectionUrl must not be null or empty");
            }
            if (Objects.isNull(clientProperties.getIdentifier()) || "".equals(clientProperties.getIdentifier())) {
                throw new NullPointerException("If client is enabled, identifier must not be null or empty");
            }
        }
    }

    @Bean("jsonClient")
    @ConditionalOnProperty(value = "spring.ocpp.server.enabled", havingValue = "true")
    @DependsOn("jsonServer")
    JSONClient jsonClientWithServer(
            ClientCoreProfile clientCoreProfile,
            ClientFirmwareManagementProfile clientFirmwareManagementProfile,
            ClientLocalAuthListProfile clientLocalAuthListProfile,
            ClientRemoteTriggerProfile clientRemoteTriggerProfile,
            ClientReservationProfile clientReservationProfile,
            ClientSmartChargingProfile clientSmartChargingProfile,
            ClientEvents clientEvents
    ) {
        return createJsonClient(clientCoreProfile, clientFirmwareManagementProfile, clientLocalAuthListProfile, clientRemoteTriggerProfile, clientReservationProfile, clientSmartChargingProfile, clientEvents);
    }

    @Bean
    JSONClient jsonClient(
            ClientCoreProfile clientCoreProfile,
            ClientFirmwareManagementProfile clientFirmwareManagementProfile,
            ClientLocalAuthListProfile clientLocalAuthListProfile,
            ClientRemoteTriggerProfile clientRemoteTriggerProfile,
            ClientReservationProfile clientReservationProfile,
            ClientSmartChargingProfile clientSmartChargingProfile,
            ClientEvents clientEvents
    ) {
        return createJsonClient(clientCoreProfile, clientFirmwareManagementProfile, clientLocalAuthListProfile, clientRemoteTriggerProfile, clientReservationProfile, clientSmartChargingProfile, clientEvents);
    }

    private JSONClient createJsonClient(
            ClientCoreProfile clientCoreProfile,
            ClientFirmwareManagementProfile clientFirmwareManagementProfile,
            ClientLocalAuthListProfile clientLocalAuthListProfile,
            ClientRemoteTriggerProfile clientRemoteTriggerProfile,
            ClientReservationProfile clientReservationProfile,
            ClientSmartChargingProfile clientSmartChargingProfile,
            ClientEvents clientEvents
    ) {
        validateClientProperties();

        JSONClient client = new JSONClient(clientCoreProfile, clientProperties.getIdentifier());
        client.addFeatureProfile(clientFirmwareManagementProfile);
        client.addFeatureProfile(clientLocalAuthListProfile);
        client.addFeatureProfile(clientRemoteTriggerProfile);
        client.addFeatureProfile(clientReservationProfile);
        client.addFeatureProfile(clientSmartChargingProfile);

        client.connect(clientProperties.getConnectionUrl(), clientEvents);

        return client;
    }
}
