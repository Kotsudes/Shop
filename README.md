# Shop

Professional JavaFX-based shop application (sample project)

## Overview

Shop is a small example Java application demonstrating a modular JavaFX UI, simple DAO-based data access, and a MySQL-backed data store. It is intended as a learning/sample project for desktop Java applications using modern Java (Java 21) and the OpenJFX toolchain.

## Features

- Modular Java project with `module-info.java`
- JavaFX UI built with FXML views
- Simple DAOs for `Clothes`, `Accessories`, and `Shoes`
- MySQL connector for persistent storage

## Technology stack

- Java 21
- JavaFX (OpenJFX)
- Maven (wrapper included)
- MySQL (connector provided)

## Requirements

- JDK 21 installed and JAVA_HOME configured
- MySQL server (if using the database features)

## Quick start

Using the included Maven wrapper from the project root.

On Windows (PowerShell or CMD):

```
.\mvnw.cmd clean javafx:run
```

On macOS / Linux:

```
./mvnw clean javafx:run
```

To build a distributable artifact:

```
.\mvnw.cmd clean package
# or on macOS / Linux: ./mvnw clean package
```

Run unit tests:

```
.\mvnw.cmd test
```

## Database configuration

This project includes a MySQL connector. Before running database features, update the connection settings in `src/main/java/com/shop/shop/database/DatabaseConnector.java` to match your MySQL instance (host, port, user, password, schema).

## Project structure (high level)

- `src/main/java` — application sources
	- `com.shop.shop` — main package and application entry (`Main.java`)
	- `com.shop.shop.controller` — JavaFX controllers
	- `com.shop.shop.dao` — data access objects
	- `com.shop.shop.database` — database connector
	- `com.shop.shop.shopclass` — domain model classes
- `src/main/resources/com/shop/shop` — FXML views and CSS

## Contributing

Contributions are welcome. Please follow these steps:

1. Fork the repository and create a feature branch.
2. Implement changes and add tests where appropriate.
3. Open a pull request with a clear description of your changes.

## Notes

- The project targets Java 21; use a matching JDK when building or running.
- The `javafx-maven-plugin` is configured for `javafx:run` in the `pom.xml`.

## License & contact

This repository is provided as-is for educational purposes. For questions or feedback, open an issue or contact the maintainer.
