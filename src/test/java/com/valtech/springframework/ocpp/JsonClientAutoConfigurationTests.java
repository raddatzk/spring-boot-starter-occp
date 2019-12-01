package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.client.*;
import com.valtech.springframework.ocpp.server.ServerCoreEventHandlerAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class JsonClientAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JsonClientAutoConfiguration.class));

    private final ApplicationContextRunner combinedContextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JsonClientAutoConfiguration.class, JsonServerAutoConfiguration.class));

    private static class ServerCoreEventHandler implements ServerCoreEventHandlerAdapter {
    }

    private static class ClientCoreEventHandler implements ClientCoreEventHandlerAdapter {
    }

    private static class ClientFirmwareManagementEventHandler implements ClientFirmwareManagementEventHandlerAdapter {
    }

    private static class ClientLocalAuthListEventHandler implements ClientLocalAuthListEventHandlerAdapter {

    }

    private static class ClientRemoteTriggerEventHandler implements ClientRemoteTriggerEventHandlerAdapter {

    }

    private static class ClientReservationEventHandler implements ClientReservationEventHandlerAdapter {

    }

    private static class ClientSmartChargingEventHandler implements ClientSmartChargingEventHandlerAdapter {

    }

    private static class ClientEvents implements ClientEventsAdapter {
    }

    @Test
    @DisplayName("Assert that context fails when client starts without required ClientCoreEventHandler bean")
    void assertThatContextFails_whenServerStartsWithoutServerCoreEventHandlerBean() {
        this.contextRunner
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true"
                )
                .run(
                        context -> assertThat(context).getFailure().hasCauseInstanceOf(UnsatisfiedDependencyException.class)

                );
    }

    @Test
    @DisplayName("Assert that client starts with enabled core profile and required properties")
    void assertThatServerExistsWithEnabledCoreProfile() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that ServerEvents bean are enabled when configured")
    void assertThatServerEventsBeanAreEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
                .withBean(ClientEvents.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(ClientEvents.class).isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that firmwareManagement is enabled when configured")
    void assertThatFirmwareManagementIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
                .withBean(ClientFirmwareManagementEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(ClientFirmwareManagementEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientFirmwareManagementProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that localAuthList is enabled when configured")
    void assertThatLocalAuthListIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
                .withBean(ClientLocalAuthListEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(ClientLocalAuthListEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientLocalAuthListProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that remoteTrigger is enabled when configured")
    void assertThatRemoteTriggerIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
                .withBean(ClientRemoteTriggerEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(ClientRemoteTriggerEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientRemoteTriggerProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that reservation is enabled when configured")
    void assertThatReservationIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
                .withBean(ClientReservationEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(ClientReservationEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientReservationProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that smartCharging is enabled when configured")
    void assertThatSmartChargingIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
                .withBean(ClientSmartChargingEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.client.enabled:true",
                        "spring.ocpp.client.connection-url:ws://localhost:8887",
                        "spring.ocpp.client.identifier:identifier"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonClient").isNotNull();
                            assertThat(context).getBean(ClientCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientCoreProfile").isNotNull();
                            assertThat(context).getBean(ClientSmartChargingEventHandler.class).isNotNull();
                            assertThat(context).getBean("clientSmartChargingProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that SSLContext is available if configured properly")
    void assertThatSSLContextIsAvailableIfConfigured() {
        this.contextRunner
                .withBean(ClientCoreEventHandler.class)
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
                .withBean(ClientCoreEventHandler.class)
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
                .withBean(ClientCoreEventHandler.class)
                .withBean(ServerCoreEventHandler.class)
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
