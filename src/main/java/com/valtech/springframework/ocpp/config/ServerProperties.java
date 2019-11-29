package com.valtech.springframework.ocpp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.ocpp.server")
public class ServerProperties {

    /**
     * Enables the server.
     * <p>
     * Defaults to false.
     */
    private Boolean enabled;

    /**
     * IP Address under which the server will be reachable.
     * <p>
     * Must not be null or empty if the server is enabled.
     */
    private String host;

    /**
     * Port under which the server will be reachable.
     * <p>
     * Must not be null or empty if the server is enabled.
     */
    private Integer port;

    /**
     * Enable SSL.
     * <p>
     * Defaults to false.
     */
    private Boolean enableSsl = false;

    @Bean
    @ConfigurationProperties(prefix = "spring.ocpp.server.ssl")
    public SSLProperties serverSSLProperties() {
        return new SSLProperties();
    }
}
