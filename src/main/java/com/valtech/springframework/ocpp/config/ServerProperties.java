package com.valtech.springframework.ocpp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.ocpp.server")
public class ServerProperties {
    private Boolean enabled;
    private String host;
    private Integer port;
}
