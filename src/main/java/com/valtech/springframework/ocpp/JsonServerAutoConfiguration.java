package com.valtech.springframework.ocpp;

import com.google.common.net.InetAddresses;
import com.valtech.springframework.ocpp.config.ServerProfilesConfig;
import com.valtech.springframework.ocpp.config.ServerProperties;
import com.valtech.springframework.ocpp.config.ServerSSLConfig;
import eu.chargetime.ocpp.JSONServer;
import eu.chargetime.ocpp.ServerEvents;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Objects;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.server.enabled", havingValue = "true")
@EnableConfigurationProperties({ServerProperties.class})
@Import({ServerProfilesConfig.class, ServerSSLConfig.class})
public class JsonServerAutoConfiguration {

    @Autowired private ServerProperties serverProperties;
    @Autowired private ServerCoreProfile serverCoreProfile;
    @Autowired(required = false) private ServerReservationProfile serverReservationProfile;
    @Autowired(required = false) private ServerSmartChargingProfile serverSmartChargingProfile;
    @Autowired(required = false) private ServerRemoteTriggerProfile serverRemoteTriggerProfile;
    @Autowired(required = false) private ServerFirmwareManagementProfile serverFirmwareManagementProfile;
    @Autowired(required = false) private ServerLocalAuthListProfile serverLocalAuthListProfile;
    @Autowired(required = false) private ServerEvents serverEvents;
    @Autowired(required = false)
    private @Qualifier("serverSSLContext")
    SSLContext sslContext;


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
    public JSONServer jsonServer() throws IOException {
        validateServerProperties();

        JSONServer server = new JSONServer(serverCoreProfile);

        if (serverFirmwareManagementProfile != null) {
            server.addFeatureProfile(serverFirmwareManagementProfile);
        }
        server.addFeatureProfile(serverReservationProfile);
        server.addFeatureProfile(serverSmartChargingProfile);
        server.addFeatureProfile(serverRemoteTriggerProfile);
        server.addFeatureProfile(serverLocalAuthListProfile);

        if (serverProperties.getEnableSsl()) {
            server.enableWSS(sslContext);
        }
        server.open(serverProperties.getHost(), serverProperties.getPort(), serverEvents);

        return server;
    }
}
