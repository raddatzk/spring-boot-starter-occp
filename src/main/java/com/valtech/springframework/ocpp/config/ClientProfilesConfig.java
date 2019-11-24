package com.valtech.springframework.ocpp.config;

import com.valtech.springframework.ocpp.client.*;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AbstractClientCoreEventHandler.class, AbstractClientFirmwareManagementEventHandler.class, AbstractClientLocalAuthListEventHandler.class, AbstractClientRemoteTriggerEventHandler.class, AbstractClientReservationEventHandler.class, AbstractClientSmartChargingEventHandler.class})
public class ClientProfilesConfig {

    @Bean
    public ClientCoreProfile clientCoreProfile(ClientCoreEventHandler clientCoreEventHandler) {
        return new ClientCoreProfile(clientCoreEventHandler);
    }

    @Bean
    public ClientFirmwareManagementProfile clientFirmwareManagementProfile(ClientFirmwareManagementEventHandler clientFirmwareManagementEventHandler) {
        return new ClientFirmwareManagementProfile(clientFirmwareManagementEventHandler);
    }

    @Bean
    public ClientLocalAuthListProfile clientLocalAuthListProfile(ClientLocalAuthListEventHandler clientLocalAuthListEventHandler) {
        return new ClientLocalAuthListProfile(clientLocalAuthListEventHandler);
    }

    @Bean
    public ClientRemoteTriggerProfile clientRemoteTriggerProfile(ClientRemoteTriggerHandler clientRemoteTriggerEventHandler) {
        return new ClientRemoteTriggerProfile(clientRemoteTriggerEventHandler);
    }

    @Bean
    public ClientReservationProfile clientReservationProfile(ClientReservationEventHandler clientReservationEventHandler) {
        return new ClientReservationProfile(clientReservationEventHandler);
    }

    @Bean
    public ClientSmartChargingProfile clientSmartChargingProfile(ClientSmartChargingEventHandler clientSmartChargingEventHandler) {
        return new ClientSmartChargingProfile(clientSmartChargingEventHandler);
    }


}
