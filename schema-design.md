# Database Schema Design

This document describes the MySQL database schema for the Hospital Appointment System.

---

## Tables

### Doctor
- doctor_id INT PRIMARY KEY AUTO_INCREMENT
- name VARCHAR(100) NOT NULL
- specialty VARCHAR(100) NOT NULL
- email VARCHAR(100) UNIQUE NOT NULL
- password VARCHAR(255) NOT NULL

### Patient
- patient_id INT PRIMARY KEY AUTO_INCREMENT
- name VARCHAR(100) NOT NULL
- email VARCHAR(100) UNIQUE NOT NULL
- phone VARCHAR(15)
- password VARCHAR(255) NOT NULL

### Appointment
- appointment_id INT PRIMARY KEY AUTO_INCREMENT
- doctor_id INT NOT NULL FOREIGN KEY REFERENCES Doctor(doctor_id)
- patient_id INT NOT NULL FOREIGN KEY REFERENCES Patient(patient_id)
- appointment_time DATETIME NOT NULL
- status VARCHAR(20) DEFAULT 'Scheduled'

### Admin
- admin_id INT PRIMARY KEY AUTO_INCREMENT
- name VARCHAR(100) NOT NULL
- email VARCHAR(100) UNIQUE NOT NULL
- password VARCHAR(255) NOT NULL

---

## Relationships
- One doctor can have many appointments.
- One patient can have many appointments.
- Admin manages doctors, patients, and appointments but does not directly link to appointment records.
