
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pedroarapua-payment-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=pedroarapua-payment-api) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=pedroarapua-payment-api&metric=bugs)](https://sonarcloud.io/dashboard?id=pedroarapua-payment-api) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pedroarapua-payment-api&metric=coverage)](https://sonarcloud.io/dashboard?id=pedroarapua-payment-api) [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=pedroarapua-payment-api&metric=security_rating)](https://sonarcloud.io/dashboard?id=pedroarapua-payment-api) [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=pedroarapua-payment-api&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=pedroarapua-payment-api)

# Payment Api

> Payment Api is an API project of Organization

Payment Api is responsible to process all payments in Organization

![image](https://blog.invoiceberry.com/wp-content/uploads/2016/05/VX680_verifone_credit_card_machine_small_business.jpg)

## Requirements
```sh
Java 11 Open
Plugin Lombok
Docker and Docker Compose
```

## Installation OS X & Linux:


**Docker compose:**

```sh
https://docs.docker.com/compose/install/
```

**Lombok plugin:**

```sh
Intellij: https://projectlombok.org/setup/intellij
Eclipse : https://projectlombok.org/setup/eclipse
```

## Environment
Config environment variables according to specific environment `src/main/application.yml`:

| Name | Description | Default Value | Required |
| -- | -- | -- | -- |
| DATASOURCE_URL | Url JDBC to connect on database | jdbc:mysql://127.0.0.1:3306/payment?useSSL=false | :white_check_mark: |
| DATASOURCE_USERNAME | Username of database | payment | :white_check_mark: |
| DATASOURCE_PASSWORD | Password of database | payment | :white_check_mark: |
| HIKARI_MINIMUM_IDLE | Minimum quantity of connection pool | 10 | :white_check_mark: |
| HIKARI_MAXIMUM_POLL_SIZE | Maximum quantity of connection pool | 10 | :white_check_mark: |
| ENV | App environment (development, staging or production) | development | :white_check_mark: |

Keep the file *src/test/application-test.yml* and table environments always up to date.

## Setup to start Development

Go to project root folder:

**Starting MySQL DataBase:**

```sh
docker-compose up -d
```

**Compiling Project:**

```sh
./mvnw clean package
```

**Running Converage:**

```sh
./mvnw clean install jacoco:report
```

Check Coverage Page: ``{workdir}/target/site/jacoco/index.html``.

**Running SonarQube:**

```sh
./mvnw sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.login={token} -Dsonar.organization=pedroarapua-github -Dsonar.projectKey=pedroarapua-payment-api -Dsonar.projectName=payment-api -Dsonar.sources=src/main/java -Dsonar.sourceEncoding=UTF-8 -Dsonar.exclusions='target/**,src/main/resources/**,src/main/java/com/organization/payment/v1/dto/**/*,src/main/java/com/organization/payment/enumeration/**/*,src/main/java/com/organization/payment/config/**/*' -Dsonar.java.binaries=target
```

[SonarQube](https://sonarcloud.io/dashboard?id=pedroarapua-payment-api).

**Running Project in local environment:**

```sh
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local
```

[Documenation](http://localhost:8080/swagger-ui.html).

