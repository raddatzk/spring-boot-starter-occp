package com.valtech.springframework.ocpp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.ocpp.server")
@Validated
public class ServerProperties {

    /**
     * Enables the server.
     *
     * Defaults to false
     */
    private Boolean enabled;

    /**
     * IP Adress under which the server will be reachable.
     *
     * Must not be null or empty if the server is enabled
     */
    private String host;

    /**
     * Port under which the server will be reachable.
     *
     * Must not be null or empty if the server is enabled
     */
    private Integer port;
}
