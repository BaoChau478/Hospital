package com.example.healthcare.controller;

import com.example.healthcare.model.Prescription;
import com.example.healthcare.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * POST endpoint to save a prescription
     * Requires: Token + Valid request body
     */
    @PostMapping
    public ResponseEntity<?> savePrescription(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody Prescription prescription) {

        // Token validation (ví dụ đơn giản: check prefix "Bearer ")
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or missing token.");
        }

        try {
            Prescription savedPrescription = prescriptionService.savePrescription(prescription);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedPrescription);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error saving prescription: " + e.getMessage());
        }
    }
}
