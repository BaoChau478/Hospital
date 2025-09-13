package com.example.hospital.controller;

import com.example.hospital.model.Doctor;
import com.example.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    // Giả sử ta có token "SECRET123" để demo
    private final String VALID_TOKEN = "SECRET123";

    @GetMapping("/availability/{id}")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // Kiểm tra token
        if (token == null || !token.equals("Bearer " + VALID_TOKEN)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or missing token");
        }

        // Tìm bác sĩ theo ID
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            return ResponseEntity.ok(doctor.get().getAvailableTimes());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Doctor not found");
        }
    }
}
