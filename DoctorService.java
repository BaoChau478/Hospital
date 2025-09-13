package app.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.models.Doctor;
import app.repositories.DoctorRepository;
import app.dto.ApiResponse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Lấy các slot trống trong ngày cho bác sĩ
    public List<LocalTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        // ví dụ: giả sử bác sĩ làm việc từ 8h đến 17h, mỗi ca 1 tiếng
        List<LocalTime> allSlots = new ArrayList<>();
        for (int hour = 8; hour < 17; hour++) {
            allSlots.add(LocalTime.of(hour, 0));
        }

        // TODO: lấy danh sách slot đã được đặt từ AppointmentRepository để lọc ra slot còn trống
        // Ở đây tạm return toàn bộ
        return allSlots;
    }

    // Xác thực login bác sĩ
    public ResponseEntity<?> validateDoctorLogin(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email).orElse(null);

        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Doctor not found"));
        }

        if (!doctor.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse(false, "Invalid credentials"));
        }

        return ResponseEntity.ok(new ApiResponse(true, "Login successful", doctor));
    }
}
