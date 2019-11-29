package com.valtech.springframework.ocpp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.client.enable-ssl", havingValue = "true")
public class ClientSSLConfig {

    @Autowired
    @Qualifier("clientSSLProperties")
    private SSLProperties sslProperties;

    @Bean("clientKeyStore")
    public KeyStore keyStore() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(sslProperties.getKeyStoreType().name());

        File keyStoreFile = new File(sslProperties.getKeyStore());
        keyStore.load(new FileInputStream(keyStoreFile), sslProperties.getStorePassword().toCharArray());

        return keyStore;
    }

    @Bean("clientKeyManagerFactory")
    public KeyManagerFactory keyManagerFactory(@Qualifier("clientKeyStore") KeyStore keyStore) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, sslProperties.getKeyPassword().toCharArray());

        return keyManagerFactory;
    }

    @Bean("clientTrustManagerFactory")
    public TrustManagerFactory trustManagerFactory(@Qualifier("clientKeyStore") KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keyStore);

        return trustManagerFactory;
    }

    @Bean("clientSSLContext")
    public SSLContext sslContext(@Qualifier("clientKeyManagerFactory") KeyManagerFactory keyManagerFactory,
                                 @Qualifier("clientTrustManagerFactory") TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}
