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
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class JSONServerAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JSONServerAutoConfiguration.class));

    @Test
    @DisplayName("Assert that context fails when server starts without required ServerCoreEventHandler bean")
    void assertThatContextFails_whenServerStartsWithoutServerCoreEventHandlerBean() {
        this.contextRunner
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true"
                )
                .run(
                        context -> assertThat(context).getFailure().hasCauseInstanceOf(UnsatisfiedDependencyException.class)

                );
    }

    @Test
    @DisplayName("Assert that server starts with enabled core profile and required properties")
    void assertThatServerExistsWithEnabledCoreProfile() {
        this.contextRunner
                .withBean(OcppTestBase.ServerCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ServerCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverCoreProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that ServerEvents bean are enabled when configured")
    void assertThatServerEventsBeanAreEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ServerCoreEventHandler.class)
                .withBean(OcppTestBase.ServerEvents.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ServerCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ServerEvents.class).isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that firmwareManagement is enabled when configured")
    void assertThatFirmwareManagementIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ServerCoreEventHandler.class)
                .withBean(OcppTestBase.ServerFirmwareManagementEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ServerCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverCoreProfile").isNotNull();
                            assertThat(context).getBean(OcppTestBase.ServerFirmwareManagementEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverFirmwareManagementProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that SSLContext is available if configured properly")
    void assertThatSSLContextIsAvailableIfConfigured() {
        this.contextRunner
                .withBean(OcppTestBase.ServerCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887",
                        "spring.ocpp.server.enable-ssl:true",
                        "spring.ocpp.server.ssl.key-password:keypassword",
                        "spring.ocpp.server.ssl.key-store-type:jks",
                        "spring.ocpp.server.ssl.key-store-path:classpath://keystore.jks",
                        "spring.ocpp.server.ssl.store-password:storepassword"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean("serverSSLContext").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that SSLContext is available if configured properly and keystore not from classpath")
    void assertThatSSLContextIsAvailableIfConfiguredAndKeyStoreNotFromClasspath() {
        this.contextRunner
                .withBean(OcppTestBase.ServerCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887",
                        "spring.ocpp.server.enable-ssl:true",
                        "spring.ocpp.server.ssl.key-password:keypassword",
                        "spring.ocpp.server.ssl.key-store-type:jks",
                        "spring.ocpp.server.ssl.key-store-path:keystore.jks",
                        "spring.ocpp.server.ssl.store-password:storepassword"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean("serverSSLContext").isNotNull();
                        }
                );
    }
}
