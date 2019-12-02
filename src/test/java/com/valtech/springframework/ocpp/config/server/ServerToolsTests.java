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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.InvalidPropertyException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServerToolsTests {

    private void assertInvalidPropertyException(String message, ServerProperties properties) {
        InvalidPropertyException e = assertThrows(InvalidPropertyException.class, () -> ServerTools.validateServerProperties(properties));

        assertTrue(e.getMessage().contains(message));
    }

    @Test
    @DisplayName("Assert that exception is thrown when server starts without required host property")
    void assertThatExceptionIsThrown_whenServerStartsWithoutHostProperty() {
        ServerProperties properties = new ServerProperties();
        properties.setEnabled(true);

        assertInvalidPropertyException("If server is enabled, host must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when host is empty")
    void assertThatExceptionIsThrown_whenHostIsEmpty() {
        ServerProperties properties = new ServerProperties();
        properties.setEnabled(true);
        properties.setHost("");

        assertInvalidPropertyException("If server is enabled, host must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when hostname is not localhost")
    void assertThatExceptionIsThrown_WhenHostnameIsNotLocalhost() {
        ServerProperties properties = new ServerProperties();
        properties.setEnabled(true);
        properties.setHost("ipaddress");

        assertInvalidPropertyException("ipaddress is not a valid host", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when host has invalid format")
    void assertThatExceptionIsThrown_WhenHostHasInvalidFormat() {
        ServerProperties properties = new ServerProperties();
        properties.setEnabled(true);
        properties.setHost("300.300.300.300");

        assertInvalidPropertyException("300.300.300.300 is not a valid host", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when server starts without required port property")
    void assertThatExceptionIsThrown_whenServerStartsWithoutPortProperty() {
        ServerProperties properties = new ServerProperties();
        properties.setEnabled(true);
        properties.setHost("127.0.0.1");

        assertInvalidPropertyException("If server is enabled, port must not be null", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when server starts on localhost without required port property")
    void assertThatExceptionIsThrown_whenServerStartsOnLocalhostWithoutPortProperty() {
        ServerProperties properties = new ServerProperties();
        properties.setEnabled(true);
        properties.setHost("localhost");

        assertInvalidPropertyException("If server is enabled, port must not be null", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when port is below 1024")
    void assertThatExceptionIsThrown_whenPortIsBelow1024() {

        ServerProperties properties = new ServerProperties();
        properties.setEnabled(true);
        properties.setHost("localhost");
        properties.setPort(0);

        assertInvalidPropertyException("Port cannot be below 1024", properties);
    }
}
