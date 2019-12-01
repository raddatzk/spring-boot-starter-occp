package com.valtech.springframework.ocpp.config.ssl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
public class SSLProperties {

    /**
     * The keystore to use.
     * <p>
     * Defaults to jks.
     */
    @NestedConfigurationProperty
    private SSLKeystoreType keyStoreType = SSLKeystoreType.JKS;

    /**
     * Path to the key store.
     */
    private String keyStorePath;

    /**
     * Password to the key in the key store.
     */
    private String keyPassword;

    /**
     * Password for the key store.
     */
    private String storePassword;

    public enum SSLKeystoreType {
        /**
         * Use a keystore of type JKS.
         */
        JKS
    }
}
