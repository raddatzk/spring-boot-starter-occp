package com.valtech.springframework.ocpp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.ocpp.client")
public class ClientProperties {

    /**
     * Enables the the client.
     *
     * Defaults to false
     */
    private Boolean enabled;

    /**
     * The server url the client tries to connect to.
     *
     * Must not be null or empty if the client is enabled
     */
    private String connectionUrl;

    /**
     * The name of the client.
     *
     * Must not be null or empty if the client is enabled
     */
    private String identifier;
}
