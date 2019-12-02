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
import com.valtech.springframework.ocpp.config.ssl.SSLProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.ocpp.client")
public class ClientProperties {

    /**
     * Type of the client.
     * <p>
     * Defaults to JSON.
     */
    @NestedConfigurationProperty
    private ConnectionType type = ConnectionType.JSON;

    /**
     * Enables the client.
     * <p>
     * Defaults to false.
     */
    private Boolean enabled = false;

    /**
     * Call back info that the server can send requests to.
     * <p>
     * Only required in SOAP client
     */
    private String soapCallback;

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
