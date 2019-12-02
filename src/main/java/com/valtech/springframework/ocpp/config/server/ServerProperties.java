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

package com.valtech.springframework.ocpp.config.server;

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
@ConfigurationProperties(prefix = "spring.ocpp.server")
public class ServerProperties {

    /**
     * Type of the client.
     * <p>
     * Defaults to JSON.
     */
    @NestedConfigurationProperty
    private ConnectionType type = ConnectionType.JSON;

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
     * Must not be null or below 1024 if the server is enabled.
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
