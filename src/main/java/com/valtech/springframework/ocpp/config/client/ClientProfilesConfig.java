package com.valtech.springframework.ocpp.config.client;

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
    @ConditionalOnBean(ClientRemoteTriggerHandler.class)
    public ClientRemoteTriggerProfile clientRemoteTriggerProfile(ClientRemoteTriggerHandler clientRemoteTriggerEventHandler) {
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
