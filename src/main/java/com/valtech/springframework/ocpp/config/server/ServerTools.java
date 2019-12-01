package com.valtech.springframework.ocpp.config.server;

import com.google.common.net.InetAddresses;
import org.springframework.beans.InvalidPropertyException;

import java.util.Objects;

public final class ServerTools {

    private ServerTools() {}

    public static void validateServerProperties(ServerProperties serverProperties) {
        if (Objects.isNull(serverProperties.getHost()) || serverProperties.getHost().isEmpty()) {
            throw new InvalidPropertyException(ServerProperties.class, "host", "If server is enabled, host must not be null or empty");
        }

        if (!(InetAddresses.isInetAddress(serverProperties.getHost()) || "localhost".equals(serverProperties.getHost()))) {
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
