package com.valtech.springframework.ocpp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
public class SSLProperties {
    @NestedConfigurationProperty
    private SslKeystoreType keyStoreType;

    /**
     * Path to the key store.
     */
    private String keyStore;

    /**
     * Password to the key in the key store.
     */
    private String keyPassword;

    /**
     * Password for the key store.
     */
    private String storePassword;

    /**
     * Name of the key-pair to use.
     */
    private String keyAlias;

    public enum SslKeystoreType {
        /**
         * Use a keystore of type JKS.
         */
        JKS
    }
}
