package com.valtech.springframework.ocpp.config.client;

import com.valtech.springframework.ocpp.config.ssl.SSLProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.ocpp.client")
public class ClientProperties {

    /**
     * Enables the the client.
     * <p>
     * Defaults to false.
     */
    private Boolean enabled;

    /**
     * The server url the client tries to connect to.
     * <p>
     * Must not be null or empty if the client is enabled.
     */
    private String connectionUrl;

    /**
     * The name of the client.
     * <p>
     * Must not be null or empty if the client is enabled.
     */
    private String identifier;

    /**
     * Enable ssl.
     * <p>
     * Defaults to false.
     */
    private Boolean enableSsl = false;

    @Bean
    @ConfigurationProperties(prefix = "spring.ocpp.client.ssl")
    public SSLProperties clientSSLProperties() {
        return new SSLProperties();
    }
}
