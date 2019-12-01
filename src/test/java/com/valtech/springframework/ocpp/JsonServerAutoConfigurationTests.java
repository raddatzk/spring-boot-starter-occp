package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.server.ServerCoreEventHandlerAdapter;
import com.valtech.springframework.ocpp.server.ServerEventsAdapter;
import com.valtech.springframework.ocpp.server.ServerFirmwareManagementEventHandlerAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class JsonServerAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(JsonServerAutoConfiguration.class));

    private static class ServerCoreEventHandler implements ServerCoreEventHandlerAdapter {
    }

    private static class ServerEvents implements ServerEventsAdapter {
    }

    private static class ServerFirmwareManagementEventHandler implements ServerFirmwareManagementEventHandlerAdapter {
    }

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
                .withBean(ServerCoreEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean(ServerCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverCoreProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that ServerEvents bean are enabled when configured")
    void assertThatServerEventsBeanAreEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ServerCoreEventHandler.class)
                .withBean(ServerEvents.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean(ServerCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverCoreProfile").isNotNull();
                            assertThat(context).getBean(ServerEvents.class).isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that firmwareManagement is enabled when configured")
    void assertThatFirmwareManagementIsEnabledWhenConfigured() {
        this.contextRunner
                .withBean(ServerCoreEventHandler.class)
                .withBean(ServerFirmwareManagementEventHandler.class)
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.host:127.0.0.1",
                        "spring.ocpp.server.port:8887"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("jsonServer").isNotNull();
                            assertThat(context).getBean(ServerCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverCoreProfile").isNotNull();
                            assertThat(context).getBean(ServerFirmwareManagementEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverFirmwareManagementProfile").isNotNull();
                        }
                );
    }

    @Test
    @DisplayName("Assert that SSLContext is available if configured properly")
    void assertThatSSLContextIsAvailableIfConfigured() {
        this.contextRunner
                .withBean(ServerCoreEventHandler.class)
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
                .withBean(ServerCoreEventHandler.class)
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
