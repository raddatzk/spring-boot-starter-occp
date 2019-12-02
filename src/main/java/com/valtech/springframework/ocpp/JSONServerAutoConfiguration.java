package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.config.server.ServerProfilesConfig;
import com.valtech.springframework.ocpp.config.server.ServerProperties;
import com.valtech.springframework.ocpp.config.server.ServerSSLConfig;
import com.valtech.springframework.ocpp.config.server.ServerTools;
import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Optional;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.server.enabled", havingValue = "true")
@EnableConfigurationProperties({ServerProperties.class})
@Import({ServerProfilesConfig.class, ServerSSLConfig.class})
public class JSONServerAutoConfiguration {

    private final ServerProperties serverProperties;
    private final ServerCoreProfile serverCoreProfile;
    private final ServerReservationProfile serverReservationProfile;
    private final ServerSmartChargingProfile serverSmartChargingProfile;
    private final ServerRemoteTriggerProfile serverRemoteTriggerProfile;
    private final ServerLocalAuthListProfile serverLocalAuthListProfile;
    private final Optional<ServerEvents> serverEvents;
    private final Optional<ServerFirmwareManagementProfile> serverFirmwareManagementProfile;
    private final Optional<SSLContext> sslContext;

    @java.lang.SuppressWarnings("squid:S00107")
    public JSONServerAutoConfiguration(ServerProperties serverProperties, ServerCoreProfile serverCoreProfile, ServerReservationProfile serverReservationProfile, ServerSmartChargingProfile serverSmartChargingProfile, ServerRemoteTriggerProfile serverRemoteTriggerProfile, ServerLocalAuthListProfile serverLocalAuthListProfile, Optional<ServerEvents> serverEvents, Optional<ServerFirmwareManagementProfile> serverFirmwareManagementProfile, @Qualifier("serverSSLContext") Optional<SSLContext> sslContext) {
        this.serverProperties = serverProperties;
        this.serverCoreProfile = serverCoreProfile;
        this.serverReservationProfile = serverReservationProfile;
        this.serverSmartChargingProfile = serverSmartChargingProfile;
        this.serverRemoteTriggerProfile = serverRemoteTriggerProfile;
        this.serverLocalAuthListProfile = serverLocalAuthListProfile;
        this.serverEvents = serverEvents;
        this.serverFirmwareManagementProfile = serverFirmwareManagementProfile;
        this.sslContext = sslContext;
    }

    @Bean
    public JSONServer jsonServer(
    ) throws IOException {
        ServerTools.validateServerProperties(serverProperties);

        JSONServer server = new JSONServer(serverCoreProfile);

        serverFirmwareManagementProfile.ifPresent(server::addFeatureProfile);
        server.addFeatureProfile(serverReservationProfile);
        server.addFeatureProfile(serverSmartChargingProfile);
        server.addFeatureProfile(serverRemoteTriggerProfile);
        server.addFeatureProfile(serverLocalAuthListProfile);

        if (sslContext.isPresent()) {
            server.enableWSS(sslContext.get());
        }
        server.open(serverProperties.getHost(), serverProperties.getPort(), serverEvents.orElse(null));

        return server;
    }
}
