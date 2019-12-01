package com.valtech.springframework.ocpp.config.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.InvalidPropertyException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientToolsTests {

    private void assertInvalidPropertyException(String message, ClientProperties properties) {
        InvalidPropertyException e = assertThrows(InvalidPropertyException.class, () -> ClientTools.validateClientProperties(properties));

        assertTrue(e.getMessage().contains(message));
    }

    @Test
    @DisplayName("Assert that exception is thrown when connectionUrl is null")
    void assertThatExceptionIsThrown_whenConnectionUrlIsNull() {
        ClientProperties properties = new ClientProperties();

        assertInvalidPropertyException("If client is enabled, connectionUrl must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when connectionUrl is empty")
    void assertThatExceptionIsThrown_whenConnectionUrlIsEmpty() {
        ClientProperties properties = new ClientProperties();
        properties.setConnectionUrl("");

        assertInvalidPropertyException("If client is enabled, connectionUrl must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when identifier is null")
    void assertThatExceptionIsThrown_whenIdentifierIsNull() {
        ClientProperties properties = new ClientProperties();
        properties.setConnectionUrl("ws://localhost:8887");

        assertInvalidPropertyException("If client is enabled, identifier must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when identifier is empty")
    void assertThatExceptionIsThrown_whenIdentifierIsEmpty() {
        ClientProperties properties = new ClientProperties();
        properties.setConnectionUrl("ws://localhost:8887");
        properties.setIdentifier("");

        assertInvalidPropertyException("If client is enabled, identifier must not be null or empty", properties);
    }
}
