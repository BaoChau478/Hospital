package com.example.healthcare.service;

import com.example.healthcare.model.Appointment;
import com.example.healthcare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Booking method: Save a new appointment
     */
    public Appointment bookAppointment(Appointment appointment) {
        // Có thể thêm validate logic: check bác sĩ rảnh, giờ hợp lệ, ...
        return appointmentRepository.save(appointment);
    }

    /**
     * Retrieve appointments for a doctor on a specific date
     */
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }
}
