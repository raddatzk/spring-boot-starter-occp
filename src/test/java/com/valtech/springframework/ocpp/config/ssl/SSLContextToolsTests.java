package com.valtech.springframework.ocpp.config.ssl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.InvalidPropertyException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SSLContextToolsTests {

    private void assertInvalidPropertyException(String message, SSLProperties sslProperties) {
        InvalidPropertyException e = assertThrows(InvalidPropertyException.class, () -> SSLContextTools.sslContextConfigurer(sslProperties));

        assertTrue(e.getMessage().contains(message));
    }

    @Test
    @DisplayName("Assert that exception is thrown when ssl is enabled and keyStorePath is null")
    void assertThatExceptionIsThrown_whenKeyStorePathIsNull() {
        SSLProperties properties = new SSLProperties();

        assertInvalidPropertyException("If ssl is enabled, path to keystore must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when ssl is enabled and keyStorePath is empty")
    void assertThatExceptionIsThrown_whenKeyStorePathIsEmpty() {
        SSLProperties properties = new SSLProperties();
        properties.setKeyStorePath("");

        assertInvalidPropertyException("If ssl is enabled, path to keystore must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when ssl is enabled and storePassword is null")
    void assertThatExceptionIsThrown_whenStorePasswordIsNull() {
        SSLProperties properties = new SSLProperties();
        properties.setKeyStorePath("classpath://keystore.jks");

        assertInvalidPropertyException("If ssl is enabled, storePassword must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when ssl is enabled and storePassword is empty")
    void assertThatExceptionIsThrown_whenStorePasswordIsEmpty() {
        SSLProperties properties = new SSLProperties();
        properties.setKeyStorePath("classpath://keystore.jks");
        properties.setStorePassword("");

        assertInvalidPropertyException("If ssl is enabled, storePassword must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when ssl is enabled and keyPassword is null")
    void assertThatExceptionIsThrown_whenKeyPasswordIsNull() {
        SSLProperties properties = new SSLProperties();
        properties.setKeyStorePath("classpath://keystore.jks");
        properties.setStorePassword("storepassword");

        assertInvalidPropertyException("If ssl  is enabled, keyPassword must not be null or empty", properties);
    }

    @Test
    @DisplayName("Assert that exception is thrown when ssl is enabled and keyPassword is empty")
    void assertThatExceptionIsThrown_whenKeyPasswordIsEmpty() {
        SSLProperties properties = new SSLProperties();
        properties.setKeyStorePath("classpath://keystore.jks");
        properties.setStorePassword("storepassword");
        properties.setKeyPassword("");

        assertInvalidPropertyException("If ssl  is enabled, keyPassword must not be null or empty", properties);
    }
}
