# Payment Api

> Payment Api is an API project of Cora

Payment Api is responsible to process all payments in Cora Bank

![image](https://www.example.com/image.jpg)

## Requirements
```sh
Java 11
Docker Compose
Plugin Lombok
Docker and Docker Compose
```

## Installation OS X & Linux:


**Java 11 - SDKMAN:**

```sh
https://sdkman.io/install
sdk i java 11.0.2-open
```

**Docker compose:**

Go to root folder and execute:

```sh
https://docs.docker.com/compose/install/
docker-compose up -d
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
| RETRY_MAX_ATTEMPTS | Maximum quantity of retries to call an API | 3 | |
| RETRY_BACK_OFF_DELAY | Delay to execute again call an API | 500 | |
| DATASOURCE_URL | Url JDBC to connect on database | | :white_check_mark: |
| DATASOURCE_USERNAME | Username of database | | :white_check_mark: |
| DATASOURCE_PASSWORD | Password of database | | :white_check_mark: |
| HIKARI_MINIMUM_IDLE | Minimum quantity of connection pool | 10 | |
| HIKARI_MAXIMUM_POLL_SIZE | Maximum quantity of connection pool | 10 | |
| 
| SECURITY_USER | User of Basic Authentication API | | :white_check_mark: |
| SECURITY_PASSWORD | Password of Basic Authentication API | | :white_check_mark: |
| JWT_SECRET_KEY | Salt do JWT | stubJWT | |
|
| SENTRY | DSN to connect in Sentry | | :white_check_mark: |
| ENV | App environment (development, staging or production) | development | |

Keep the file *src/test/application-test.yml* and table environments always up to date.

**Getting environment variables:**

```
  @Value("${datasource.url}")
  private String datasourceUrl;
```

## Setup to start Development

Go to project root folder:

**Compiling Project:**

```sh
sdk use java 11.0.2-open
./mvnw clean package
```

**Running Converage:**

```sh
sdk use java 11.0.2-open
./mvnw clean install jacoco:report
```

**Running Project in local environment:**

```sh
sdk use java 11.0.2-open
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=local
```
