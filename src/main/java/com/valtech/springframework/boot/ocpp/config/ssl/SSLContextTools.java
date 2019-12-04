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

package com.valtech.springframework.boot.ocpp.config.ssl;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

public final class SSLContextTools {

    private SSLContextTools() {}

    private static void validateSSLProperties(SSLProperties sslProperties) {
        if (sslProperties.getKeyStorePath() == null || sslProperties.getKeyStorePath().isEmpty()) {
            throw new InvalidPropertyException(SSLProperties.class, "key-store-path", "If ssl is enabled, path to keystore must not be null or empty");
        }
        if (sslProperties.getStorePassword() == null || sslProperties.getStorePassword().isEmpty()) {
            throw new InvalidPropertyException(SSLProperties.class, "store-password", "If ssl is enabled, storePassword must not be null or empty");
        }
        if (sslProperties.getKeyPassword() == null || sslProperties.getKeyPassword().isEmpty()) {
            throw new InvalidPropertyException(SSLProperties.class, "key-password", "If ssl  is enabled, keyPassword must not be null or empty");
        }
    }

    private static KeyStore createKeyStore(SSLProperties sslProperties) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {
        validateSSLProperties(sslProperties);

        KeyStore keyStore = KeyStore.getInstance(sslProperties.getKeyStoreType().name());

        InputStream is;
        if (sslProperties.getKeyStorePath().startsWith("classpath://")) {
            is = (new ClassPathResource(sslProperties.getKeyStorePath().replace("classpath://", ""))).getInputStream();
        } else {
            is = new FileInputStream(new File(sslProperties.getKeyStorePath()));
        }
        keyStore.load(is, sslProperties.getStorePassword().toCharArray());

        return keyStore;
    }

    private static KeyManagerFactory createKeyManagerFactory(KeyStore keyStore, SSLProperties sslProperties) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, sslProperties.getKeyPassword().toCharArray());

        return keyManagerFactory;
    }

    private static TrustManagerFactory createTrustManagerFactory(KeyStore keyStore) throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        return trustManagerFactory;
    }

    public static SSLContext sslContextConfigurer(SSLProperties sslProperties) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, KeyManagementException {
        KeyStore keyStore = createKeyStore(sslProperties);
        KeyManagerFactory keyManagerFactory = createKeyManagerFactory(keyStore, sslProperties);
        TrustManagerFactory trustManagerFactory = createTrustManagerFactory(keyStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}
