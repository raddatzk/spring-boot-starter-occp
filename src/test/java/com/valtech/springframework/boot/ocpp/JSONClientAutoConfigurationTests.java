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

class JSONClientAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JSONClientAutoConfiguration.class));

    private final ApplicationContextRunner combinedContextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JSONClientAutoConfiguration.class, JSONServerAutoConfiguration.class));

    @Test
    @DisplayName("Assert that context fails when client starts without required ClientCoreEventHandler bean")
    void assertThatContextFails_whenServerStartsWithoutServerCoreEventHandlerBean() {
        this.contextRunner
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true"
                )
                .run(
                        context -> assertThat(context).getFailure().hasRootCauseInstanceOf(NoSuchBeanDefinitionException.class)

                );
    }

    @Test
    @DisplayName("Assert that client starts with enabled core profile and required properties")
    void assertThatServerExistsWithEnabledCoreProfile() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that serverEvents are enabled when configured")
    void assertThatServerEventsAreEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withBean(OcppTestBase.ClientEvents.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
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
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
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
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
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
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
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
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
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
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ClientSmartChargingEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientSmartChargingProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that SSLContext is available if configured properly")
    void assertThatSSLContextIsAvailableIfConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.enable-ssl:true",
                        "spring.ocpp.client.ssl.key-password:keypassword",
                        "spring.ocpp.client.ssl.key-store-type:jks",
                        "spring.ocpp.client.ssl.key-store-path:classpath://keystore.jks",
                        "spring.ocpp.client.ssl.store-password:storepassword"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean("clientSSLContext").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that SSLContext is available if configured properly and keystore not from classpath")
    void assertThatSSLContextIsAvailableIfConfiguredAndKeyStoreNotFromClasspath() {
        this.contextRunner
                .withBean(OcppTestBase.ClientCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier",
                        "spring.ocpp.client.enable-ssl:true",
                        "spring.ocpp.client.ssl.key-password:keypassword",
                        "spring.ocpp.client.ssl.key-store-type:jks",
                        "spring.ocpp.client.ssl.key-store-path:keystore.jks",
                        "spring.ocpp.client.ssl.store-password:storepassword"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean("clientSSLContext").isNotNull();
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
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean("jsonServer").isNotNull();
                        }
                );
    }
}
