package com.valtech.springframework.ocpp.config;

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
