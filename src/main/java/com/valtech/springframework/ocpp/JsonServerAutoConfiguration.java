package com.valtech.springframework.ocpp;

import com.google.common.net.InetAddresses;
import com.valtech.springframework.ocpp.config.ServerProfilesConfig;
import com.valtech.springframework.ocpp.config.ServerProperties;
import com.valtech.springframework.ocpp.server.ServerEventsConfigurer;
import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Objects;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.server.enabled", havingValue = "true")
@EnableConfigurationProperties({ServerProperties.class})
@Import({ServerProfilesConfig.class, ServerEventsConfigurer.class})
public class JsonServerAutoConfiguration {

    @Autowired private ServerProperties serverProperties;

    private void validateServerProperties() {
        if (serverProperties.getEnabled()) {
            if (Objects.isNull(serverProperties.getHost()) || "".equals(serverProperties.getHost())) {
                throw new InvalidPropertyException(ServerProperties.class, "host", "If server is enabled, host must not be null or empty");
            }

            if (InetAddresses.isInetAddress(serverProperties.getHost())) {
                throw new InvalidPropertyException(ServerProperties.class, "host", String.format("%s is not a valid host", serverProperties.getHost()));
            }

            if (Objects.isNull(serverProperties.getPort())) {
                throw new InvalidPropertyException(ServerProperties.class, "port", "If server is enabled, port must not be null");
            }

            if (serverProperties.getPort() <= 1024) {
                throw new InvalidPropertyException(ServerProperties.class, "port", "Port cannot be below 1024");
            }
        }
    }

    @Bean
    public JSONServer jsonServer(
            ServerCoreProfile serverCoreProfile,
            ServerReservationProfile serverReservationProfile,
            ServerSmartChargingProfile serverSmartChargingProfile,
            ServerRemoteTriggerProfile serverRemoteTriggerProfile,
            ServerFirmwareManagementProfile serverFirmwareManagementProfile,
            ServerLocalAuthListProfile serverLocalAuthListProfile,
            ServerEvents serverEvents
    ) {
        validateServerProperties();

        JSONServer server = new JSONServer(serverCoreProfile);
        server.addFeatureProfile(serverReservationProfile);
        server.addFeatureProfile(serverSmartChargingProfile);
        server.addFeatureProfile(serverRemoteTriggerProfile);
        server.addFeatureProfile(serverFirmwareManagementProfile);
        server.addFeatureProfile(serverLocalAuthListProfile);

        server.open(serverProperties.getHost(), serverProperties.getPort(), serverEvents);

        return server;
    }
}
