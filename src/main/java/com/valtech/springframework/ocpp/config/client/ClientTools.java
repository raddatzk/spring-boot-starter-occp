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

package com.valtech.springframework.ocpp.config.client;

import com.valtech.springframework.ocpp.ConnectionType;
import org.springframework.beans.InvalidPropertyException;

import java.util.Objects;

public final class ClientTools {

    private ClientTools() {
    }

    public static void validateClientProperties(ClientProperties clientProperties) {
        if (Objects.isNull(clientProperties.getConnectionUrl()) || clientProperties.getConnectionUrl().isEmpty()) {
            throw new InvalidPropertyException(ClientProperties.class, "connectionUrl", "If client is enabled, connectionUrl must not be null or empty");
        }
        if (Objects.isNull(clientProperties.getIdentifier()) || clientProperties.getIdentifier().isEmpty()) {
            throw new InvalidPropertyException(ClientProperties.class, "identifier", "If client is enabled, identifier must not be null or empty");
        }
        if ((clientProperties.getType() == ConnectionType.SOAP) && (Objects.isNull(clientProperties.getSoapCallback()) || clientProperties.getSoapCallback().isEmpty())) {
                throw new InvalidPropertyException(ClientProperties.class, "soapCallback", "If connection is SOAP, soapCallback must not be null or empty");
        }
    }


}
