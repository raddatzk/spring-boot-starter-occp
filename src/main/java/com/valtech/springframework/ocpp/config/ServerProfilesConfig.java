package com.valtech.springframework.ocpp.config;

import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.valtech.springframework.ocpp.server.ServerCoreEventHandlerConfigurer;
import com.valtech.springframework.ocpp.server.ServerFirmwareManagementEventHandlerConfigurer;

@Configuration
@Import({ServerCoreEventHandlerConfigurer.class, ServerFirmwareManagementEventHandlerConfigurer.class})
public class ServerProfilesConfig {

    @Bean
    public ServerCoreProfile serverCoreProfile(ServerCoreEventHandler serverCoreEventHandler) {

        return new ServerCoreProfile(serverCoreEventHandler);
    }

    @Bean
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
