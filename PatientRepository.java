package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.models.Patient;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Derived query: tìm bệnh nhân bằng email
    Optional<Patient> findByEmail(String email);

    // Derived query: tìm bệnh nhân bằng email hoặc số điện thoại
    Optional<Patient> findByEmailOrPhoneNumber(String email, String phoneNumber);

    // Custom query (cách khác)
    @Query("SELECT p FROM Patient p WHERE p.email = :email OR p.phoneNumber = :phoneNumber")
    Optional<Patient> findPatientByEmailOrPhone(String email, String phoneNumber);
}
