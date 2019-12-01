package com.valtech.springframework.ocpp.config.client;

import com.valtech.springframework.ocpp.config.ssl.SSLContextTools;
import com.valtech.springframework.ocpp.config.ssl.SSLProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Configuration
@ConditionalOnProperty(value = "spring.ocpp.client.enable-ssl", havingValue = "true")
public class ClientSSLConfig {

    @Bean("clientSSLContext")
    public SSLContext sslContext(@Qualifier("clientSSLProperties") SSLProperties sslProperties) throws NoSuchAlgorithmException, KeyManagementException, UnrecoverableKeyException, CertificateException, KeyStoreException, IOException {

        return SSLContextTools.sslContextConfigurer(sslProperties);
    }
}
