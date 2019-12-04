/*
 *
 *  * Copyright 2019 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.valtech.springframework.boot.ocpp.config.server;

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
