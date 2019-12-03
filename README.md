[![codecov](https://codecov.io/gh/kevinraddatz/spring-boot-starter-occp/branch/master/graph/badge.svg)](https://codecov.io/gh/kevinraddatz/spring-boot-starter-occp)

# Spring Boot Starter OCPP

This project aims to provide a spring boot starter for [ocpp](https://www.openchargealliance.org/)

#### Note

Currently this starter is not available on [Maven Central](https://mvnrepository.com/). <br>
For now follow those steps:
* `cd /tmp`
* `git clone https://github.com/kevinraddatz/spring-boot-starter-occp.git`
* `cd spring-boot-starter-occp`
* `./gradlew publishToMavenLocal`

If you are using gradle, don't forget to include `mavenLocal()` in the root `repositories {}` section in your build.gradle.

## Getting started

The library is available on [Maven Central](https://mvnrepository.com/), so you can use Gradle and Maven to include this starter in your project.

### Maven

Add this snipped to your dependencies section of the pom:
``` xml
    <dependency>
        <groupId>com.valtech.springframework</groupId>
        <artifactId>spring-boot-starter-ocpp</artifactId>
        <version>1.0.0.RELEASE</version>
    </dependency>
```

### Gradle

Add this dependency to your dependencies section of the build.gradle:
``` groovy
    implementation group:'com.valtech.springframework', name: 'spring-boot-starter-ocpp', version: '1.0.0.RELEASE'
```

## Configuration

There are a few configurations you can do:

### Client

#### spring.ocpp.client.enabled

Enables the client. <br>
Defaults to false.

#### spring.ocpp.client.type

Specify the type of connection. <br>
Defaults to json.

#### spring.ocpp.client.soap-callback

Only required if `spring.ocpp.client.type=soap`. <br>
Specify host and port for callback from server to client.

#### spring.ocpp.client.identifier

Required if `spring.ocpp.client.enabled=true`. <br>
Specify the name of the charging station.

#### spring.ocpp.client.connection-url

Required if `spring.ocpp.client.enabled=true`. <br>
Specify the connection url of the server, ex. "ws://management.example.com:8887". <br>
Use wss:// for a ssl connection

#### spring.ocpp.client.enable-ssl

Import a self-signed certificate if required. <br>
Defaults to false.

#### spring.ocpp.client.ssl

See [ssl](#SSL)

### Server

#### spring.ocpp.server.enabled

Enables the server. <br>
Defaults to false.

#### spring.ocpp.server.type

Specify the type of connection. <br>
Defaults to json.

#### spring.ocpp.server.host

Required if `spring.ocpp.server.enabled=true`. <br>
IP Address under which the server will be reachable. <br>
Must not be null or empty if the server is enabled.

#### spring.ocpp.server.port

Required if `spring.ocpp.server.enabled=true`. <br>
Port under which the server will be reachable. <br>
Must not be null or below 1024 if the server is enabled.

#### spring.ocpp.server.enable-ssl

Import a certificate to enable connections via wss. <br>
Defaults to false.

#### spring.ocpp.server.ssl

See [ssl](#SSL)

### SSL

#### spring.ocpp.{server/client}.key-store-type

Required if `spring.ocpp.{server/client}.enable-ssl=true` <br>
Specify the keystore to use. <br>
Currently available: jks <br>
Defaults to jks.

#### spring.ocpp.{server/client}.key-store-path

Required if `spring.ocpp.{server/client}.enable-ssl=true` <br>
Specify the path to the key store. <br>
Can be from external of classpath

#### spring.ocpp.{server/client}.key-password

Required if `spring.ocpp.{server/client}.enable-ssl=true` <br>
Specify the password to the key in the key store. <br>

#### spring.ocpp.{server/client}.store-password

Required if `spring.ocpp.{server/client}.enable-ssl=true` <br>
Specify the password for the key store. <br>
