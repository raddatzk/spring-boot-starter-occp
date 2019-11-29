package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.config.ClientProfilesConfig;
import com.valtech.springframework.ocpp.config.ClientProperties;
import com.valtech.springframework.ocpp.config.ClientSSLConfig;
import eu.chargetime.ocpp.ClientEvents;
import eu.chargetime.ocpp.JSONClient;
import eu.chargetime.ocpp.feature.profile.*;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Objects;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.client.enabled", havingValue = "true")
@EnableConfigurationProperties({ClientProperties.class})
@Import({ClientProfilesConfig.class, ClientSSLConfig.class})
public class JsonClientAutoConfiguration {

    @Autowired private ClientProperties clientProperties;

    @Autowired private ClientCoreProfile clientCoreProfile;
    @Autowired(required = false) private ClientFirmwareManagementProfile clientFirmwareManagementProfile;
    @Autowired(required = false) private ClientLocalAuthListProfile clientLocalAuthListProfile;
    @Autowired(required = false) private ClientRemoteTriggerProfile clientRemoteTriggerProfile;
    @Autowired(required = false) private ClientReservationProfile clientReservationProfile;
    @Autowired(required = false) private ClientSmartChargingProfile clientSmartChargingProfile;
    @Autowired(required = false) private ClientEvents clientEvents;
    @Autowired(required = false)
    @Qualifier("clientSSLContext")
    private SSLContext sslContext;

    private void validateClientProperties() {
        if (clientProperties.getEnabled()) {
            if (Objects.isNull(clientProperties.getConnectionUrl()) || "".equals(clientProperties.getConnectionUrl())) {
                throw new InvalidPropertyException(ClientProperties.class, "connectionUrl", "If client is enabled, connectionUrl must not be null or empty");
            }
            if (Objects.isNull(clientProperties.getIdentifier()) || "".equals(clientProperties.getIdentifier())) {
                throw new InvalidPropertyException(ClientProperties.class, "identifier", "If client is enabled, identifier must not be null or empty");
            }
        }
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
        validateClientProperties();

        JSONClient client = new JSONClient(clientCoreProfile, clientProperties.getIdentifier());

        if (clientFirmwareManagementProfile != null) {
            client.addFeatureProfile(clientFirmwareManagementProfile);
        }
        if (clientLocalAuthListProfile != null) {
            client.addFeatureProfile(clientLocalAuthListProfile);
        }
        if (clientRemoteTriggerProfile != null) {
            client.addFeatureProfile(clientRemoteTriggerProfile);
        }
        if (clientReservationProfile != null) {
            client.addFeatureProfile(clientReservationProfile);
        }
        if (clientSmartChargingProfile != null) {
            client.addFeatureProfile(clientSmartChargingProfile);
        }

        if (clientProperties.getEnableSsl()) {
            client.enableWSS(sslContext);
        }

        client.connect(clientProperties.getConnectionUrl(), clientEvents);

        return client;
    }
}
