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

package com.valtech.springframework.boot.ocpp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class SOAPClientAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SOAPClientAutoConfiguration.class));

    private final ApplicationContextRunner combinedContextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SOAPClientAutoConfiguration.class, SOAPServerAutoConfiguration.class));

    @Test
    @DisplayName("Assert that context fails when client starts without required ClientCoreEventHandler bean")
    void assertThatContextFails_whenClientStartsWithoutRequiredClientCoreEventHandlerBean() {
        this.contextRunner
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.type:soap"
                )
                .run(
                        context -> assertThat(context).getFailure().hasRootCauseInstanceOf(NoSuchBeanDefinitionException.class)
                );
    }

    @Test
    @DisplayName("Assert that client starts with activated core profile and required properties")
    void assertThatClientStartsWithActivatedCoreProfileAndRequiredProperties() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8890"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that clientEvents are enabled when configured")
    void assertThatClientEventsAreEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ClientEvents.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8891"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientEvents.class).isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that firmwareManagement is enabled when configured")
    void assertThatFirmwareManagementIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ClientFirmwareManagementEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8892"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientFirmwareManagementEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientFirmwareManagementProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that localAuthList is enabled when configured")
    void assertThatLocalAuthListIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ClientLocalAuthListEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8893"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientLocalAuthListEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientLocalAuthListProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that remoteTrigger is enabled when configured")
    void assertThatRemoteTriggerIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ClientRemoteTriggerEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8894"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientRemoteTriggerEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientRemoteTriggerProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that reservation is enabled when configured")
    void assertThatReservationIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ClientReservationEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8895"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientReservationEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientReservationProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that smartCharging is enabled when configured")
    void assertThatSmartChargingIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ClientSmartChargingEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8896"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientSmartChargingEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientSmartChargingProfile").isNotNull();
                        }
                );
    }


    @Test
    @DisplayName("Assert that client waits for server when server is also enabled")
    void assertThatClientWaitsForServer_whenServerIsAlsoEnabled() {
        this.combinedContextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ServerCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:localhost",
                        "spring.ocpp.server.port:8887",
                        "spring.ocpp.server.type:soap",
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.type:soap",
                        "spring.ocpp.client.soap-callback:http://localhost:8897"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapClient").isNotNull();
                            assertThat(context).getBean("soapServer").isNotNull();
                        }
                );
    }

}
