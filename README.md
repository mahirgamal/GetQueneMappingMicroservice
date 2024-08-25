
# Get Queue Mapping Microservice

## Overview

The `GetQueueMappingMicroservice` project is a Java-based microservice designed to handle the retrieval of queue mappings. This microservice allows users to fetch specific mappings between publishers and consumer queues based on event types. It integrates with a MySQL database to store and retrieve these mappings and is deployed using Azure Functions for scalability and efficient processing.

## Related Projects

- [LEI Schema](https://github.com/mahirgamal/LEI-schema): Defines the standardized schema for livestock event information.
- [LEISA](https://github.com/mahirgamal/LEISA): The architecture framework for sharing livestock event information.
- [LEI2JSON](https://github.com/mahirgamal/LEI2JSON): A tool to convert LEI data into JSON format for easy processing.
- [AgriVet Treatment Grapher](https://github.com/mahirgamal/AgriVet-Treatment-Grapher): A Python-based tool designed to visualize treatment data for animals, helping veterinarians and researchers analyze treatment patterns and dosages.
- [Cattle Location Monitor](https://github.com/mahirgamal/Cattle-Location-Monitor): A system that monitors cattle location using GPS data to provide real-time insights into cattle movements and positioning.

## Features

- **Queue Mapping Retrieval**: Provides functionalities for fetching queue mappings based on publisher ID, consumer queue name, or event type.
- **Database Integration**: Uses MySQL for storing and retrieving queue mapping information.
- **Authorization**: Ensures only authorized users can fetch queue mappings.
- **REST API**: Exposes RESTful endpoints for managing the retrieval of queue mappings.

## Architecture

The application is structured using a modular architecture, aligning with Domain-Driven Design (DDD) principles to ensure scalability, maintainability, and alignment with business logic. The main components include:

### 1. Function Layer (`com.function`)

- **Purpose**: Acts as the Application Layer in DDD. This layer contains the entry points to the application, handling incoming requests for retrieving queue mappings.

- **Components**:
  - **`Function.java`**: Contains core functions that serve as the entry point for processing requests to get queue mappings.
  - **`QueueMappingResponse.java`**: Defines the structure for queue mapping responses, including the necessary fields to return.

### 2. Domain Layer (`com.domain`)

- **Purpose**: Contains the business logic for managing queue mappings, ensuring data integrity, and enforcing business rules for retrieving mappings.

- **Components**:
  - **`GetQueueMapping.java`**: Represents the domain service responsible for handling business logic related to retrieving queue mappings, such as querying the database and formatting the results.
  - **`Authentication.java`**: Manages the authentication logic, ensuring that only authorized requests are processed.

### 3. Infrastructure Layer (`src/main/resources`)

- **Purpose**: Supports the infrastructure needs of the application, handling configurations, database connections, and other essential setups.

- **Components**:
  - **`mysqlconfig.json`**: Configuration for connecting to the MySQL database, managing queue mapping data.

## Project Structure

```
/GetQueueMappingMicroservice
│
├── .git                      # Git configuration directory
├── .gitignore                # Git ignore file
├── host.json                 # Configuration file for hosting on Azure Functions
├── local.settings.json       # Local environment settings file
├── pom.xml                   # Project Object Model file for Maven
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       ├── config
│   │   │       │   └── DatabaseConfig.java        # Manages database connection settings
│   │   │       ├── domain
│   │   │       │   ├── Authentication.java        # Handles authentication logic
│   │   │       │   └── GetQueueMapping.java       # Core logic for retrieving queue mappings
│   │   │       └── function
│   │   │           ├── Function.java              # Entry point for HTTP requests to get queue mappings
│   │   │           └── QueueMappingResponse.java  # Structure for queue mapping responses
│   │   └── resources
│   │       └── mysqlconfig.json                   # MySQL configuration file
│   └── test
│       └── java
│           └── com
│               └── function
│                   ├── FunctionTest.java          # Unit tests for Function class
│                   └── HttpResponseMessageMock.java # Mocking HTTP responses for tests
│
└── target                      # Directory for compiled classes and build artifacts
```

## Requirements

- **Java 8** or higher
- **Maven** for building the project and managing dependencies
- **MySQL** for database operations
- **Azure Functions** (if deploying in Azure environment)

## Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/GetQueueMappingMicroservice.git
   ```
2. **Navigate to the Project Directory:**
   ```bash
   cd GetQueueMappingMicroservice
   ```
3. **Configure Database:**
   - Update the `mysqlconfig.json` file with your MySQL connection details.

4. **Build the Project using Maven:**
   ```bash
   mvn clean install
   ```
5. **Run the Application:**
   ```bash
   java -jar target/GetQueueMappingMicroservice-1.0-SNAPSHOT.jar
   ```

## Usage

1. **Get Queue Mapping:**
   - Use the `Function` class to handle HTTP requests for retrieving queue mappings.
   - Example usage:
     ```java
     GET /api/getQueueMapping?publisherId=123
     ```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

If you have any questions, suggestions, or need assistance, please don't hesitate to contact us.
