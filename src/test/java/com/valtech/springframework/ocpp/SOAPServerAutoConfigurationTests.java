package com.valtech.springframework.ocpp;

import com.valtech.springframework.ocpp.OcppTestBase.ServerCoreEventHandler;
import com.valtech.springframework.ocpp.OcppTestBase.ServerEvents;
import com.valtech.springframework.ocpp.OcppTestBase.ServerFirmwareManagementEventHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class SOAPServerAutoConfigurationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SOAPServerAutoConfiguration.class));

    @Test
    @DisplayName("Assert that context fails when server starts without required ServerCoreEventHandler bean")
    void assertThatContextFails_whenServerStartsWithoutServerCoreEventHandlerBean() {
        this.contextRunner
                .withPropertyValues(
                        "spring.ocpp.server.enabled:true",
                        "spring.ocpp.server.type:soap"
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
                        "spring.ocpp.server.port:8887",
                        "spring.ocpp.server.type:soap"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapServer").isNotNull();
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
                        "spring.ocpp.server.port:8887",
                        "spring.ocpp.server.type:soap"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapServer").isNotNull();
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
                        "spring.ocpp.server.port:8887",
                        "spring.ocpp.server.type:soap"
                )
                .run(
                        context -> {
                            assertThat(context).getBean("soapServer").isNotNull();
                            assertThat(context).getBean(ServerCoreEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverCoreProfile").isNotNull();
                            assertThat(context).getBean(ServerFirmwareManagementEventHandler.class).isNotNull();
                            assertThat(context).getBean("serverFirmwareManagementProfile").isNotNull();
                        }
                );
    }
}
