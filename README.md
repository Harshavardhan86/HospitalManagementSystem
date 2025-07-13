# 🏥 Hospital Management System (Java + MySQL)

A console-based Hospital Management System developed using **Java** and **MySQL**, featuring **patients**, **doctors**, and **hospital management** modules. JDBC is used to handle database operations like adding, retrieving, and managing records.

---

## 📌 Features

### 👨‍⚕️ Doctors
- Add new doctor profiles
- View doctor information
- Store specialization and availability

### 🧑‍🦽 Patients
- Register patient details
- Retrieve patient records
- Update treatment status

### 🏢 Management
- Record department details
- Track staff availability
- View hospital statistics (if implemented)

---

## 🛠️ Technologies Used

- Java (Core Java, JDBC)
- MySQL (database)
- SQL (DDL/DML queries)
- Console Input (`Scanner`)
- Any IDE (Eclipse, IntelliJ, VS Code)

---

## 📂 Database Schema Overview

### `patients` Table


###sql
CREATE TABLE patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    illness VARCHAR(255)
);

CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    specialization VARCHAR(100),
    availability VARCHAR(50)
);

CREATE DATABASE hospital;
USE hospital;
-- Then run the table creation queries above.

private static final String url = "jdbc:mysql://localhost:3306/hospital";
private static final String username = "root";
private static final String password = "yourpassword";

javac HospitalManagementSystem.java
java HospitalManagementSystem

