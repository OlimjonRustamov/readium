package uz.o_rustamov.readium.attendance.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.user.model.User;

public interface AttendanceService {

    HttpEntity<ApiResponse> getMyAttendances(User user, long courseId);

    HttpEntity<ApiResponse> getUserAttendances(long userId, long courseId);

    HttpEntity<ApiResponse> createAttendance(long studentId, long courseId, long date, boolean isPresent);

    HttpEntity<ApiResponse> editAttendance(long attendanceId, boolean isPresent);
}
