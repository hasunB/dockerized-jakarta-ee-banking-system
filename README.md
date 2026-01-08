# 🏦 Dockerized Jakarta EE Banking System

A robust, multi-module **Jakarta EE 10** banking application, fully containerized using **Docker** and **Docker Compose**.

This project demonstrates how to modernize a legacy-style "Big Iron" Java application (EAR packaging) by migrating it to a containerized environment. It solves complex Enterprise startup challenges using a custom orchestration script that guarantees strict ordering between the Database, Connection Pools, and Application Deployment.

## 🚀 Tech Stack

* **Framework:** Jakarta EE 10 (JPA, Hibernate, EJB, Servlet)
* **Build Tool:** Maven (Multi-module Project)
* **Application Server:** Payara 6 (Full Profile)
* **Database:** MySQL 8.0
* **Containerization:** Docker & Docker Compose

## 🏗️ Architecture

The project follows a classic Enterprise Java architecture packaged as an EAR (Enterprise Archive):
* `core`: Domain models (Entities) and Persistence logic.
* `web`: The web presentation layer.
* `service`/`ejb`: Business logic (Account management, Transactions).
* `ear`: Final packaging module.

### Docker Strategy
This repository uses a **Separation of Concerns** strategy, producing two portable artifacts:

1.  **Database Image (`banking-db`):**
    * Based on `mysql:8.0`.
    * The schema (`database.sql`) is "baked" directly into the image, ensuring the database is pre-initialized with tables and users upon startup anywhere.

2.  **Application Image (`banking-app`):**
    * Based on `payara/server-full`.
    * Contains the compiled EAR file, MySQL JDBC drivers, and a custom **Supervisor Script**.
    * The Supervisor Script (`run_payara.sh`) manages the lifecycle to prevent race conditions:
        1.  Starts Payara in the background.
        2.  Creates JDBC Connection Pools & Resources via `asadmin`.
        3.  **Verifies** the connection with a Ping.
        4.  Deploys the application only after the infrastructure is ready.

## ⚙️ Configuration Details

The Docker setup handles several critical configurations automatically:

* **Self-Contained Database:** No local volume mounting is required for the schema; it is distributed within the image.
* **Driver Injection:** The MySQL Connector/J driver is copied into the Payara domain library folder during the build.
* **Race Condition Resolution:**
    * The `docker-compose` `healthcheck` ensures Payara waits until MySQL is healthy.
    * The internal `run_payara.sh` script waits for the Admin Console (port 4848) before attempting configuration.
* **MySQL 8 Compatibility:** The connection pool is configured with `allowPublicKeyRetrieval=true` and `useSSL=false` to support modern MySQL authentication.

## 🛠️ How to Run (Distribution Mode)

You do not need Java, Maven, or SQL files installed. You only need Docker.

1.  Create a `docker-compose.yaml` file with the following content:

    ```yaml
    version: "3.9"
    services:
      mysql:
        image: hasunb/jakarta-ee-multi-module-banking-system-db:latest
        container_name: bank_mysql
        restart: always
        healthcheck:
          test: [ "CMD", "mysqladmin", "ping", "-h", "localhost" ]
          interval: 5s
          timeout: 5s
          retries: 10
    
      payara:
        image: hasunb/jakarta-ee-multi-module-banking-system:latest
        container_name: bank_payara
        depends_on:
          mysql:
            condition: service_healthy
        ports:
          - "8080:8080"
          - "4848:4848"
    ```

2.  **Start the application:**
    ```bash
    docker-compose up
    ```

3.  **Access the application:**
    * **Web App:** `http://localhost:8080/Banking-System`
    * **Payara Admin Console:** `http://localhost:4848` (User: `admin`, No Password)

## 🔨 Development Build Instructions

If you want to modify the code and rebuild the images yourself:

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/hasunB/dockerized-jakarta-ee-banking-system.git
    cd dockerized-jakarta-ee-banking-system
    ```

2.  **Build and Run Locally:**
    ```bash
    docker-compose up --build
    ```
