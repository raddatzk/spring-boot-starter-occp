package com.valtech.springframework.ocpp.config.client;

import org.springframework.beans.InvalidPropertyException;

import java.util.Objects;

public final class ClientTools {

    private ClientTools() {}

    public static void validateClientProperties(ClientProperties clientProperties) {
        if (Objects.isNull(clientProperties.getConnectionUrl()) || clientProperties.getConnectionUrl().isEmpty()) {
            throw new InvalidPropertyException(ClientProperties.class, "connectionUrl", "If client is enabled, connectionUrl must not be null or empty");
        }
        if (Objects.isNull(clientProperties.getIdentifier()) || clientProperties.getIdentifier().isEmpty()) {
            throw new InvalidPropertyException(ClientProperties.class, "identifier", "If client is enabled, identifier must not be null or empty");
        }
    }


}
