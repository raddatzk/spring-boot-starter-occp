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
@ConditionalOnProperty(value = "spring.ocpp.server.enable-ssl", havingValue = "true")
public class ServerSSLConfig {

    @Autowired
    @Qualifier("serverSSLProperties")
    private SSLProperties sslProperties;

    @Bean("serverKeyStore")
    public KeyStore keyStore() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance(sslProperties.getKeyStoreType().name());

        File keyStoreFile = new File(sslProperties.getKeyStore());
        keyStore.load(new FileInputStream(keyStoreFile), sslProperties.getStorePassword().toCharArray());

        return keyStore;
    }

    @Bean("serverKeyManagerFactory")
    public KeyManagerFactory keyManagerFactory(@Qualifier("serverKeyStore") KeyStore keyStore) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, sslProperties.getKeyPassword().toCharArray());

        return keyManagerFactory;
    }

    @Bean("serverTrustManagerFactory")
    public TrustManagerFactory trustManagerFactory(@Qualifier("serverKeyStore") KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        trustManagerFactory.init(keyStore);

        return trustManagerFactory;
    }

    @Bean("serverSSLContext")
    public SSLContext sslContext(@Qualifier("serverKeyManagerFactory") KeyManagerFactory keyManagerFactory,
                                 @Qualifier("serverTrustManagerFactory") TrustManagerFactory trustManagerFactory) throws NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}
