package uz.o_rustamov.readium.student_grades.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.user.model.User;

public interface StudentGradeService {

    HttpEntity<ApiResponse> getMyGrades(User user, int page, int size);

    HttpEntity<ApiResponse> getUserGrades(long studentId, int page, int size);

    HttpEntity<ApiResponse> assessStudent(long studentId, long assignmentId, int grade);

    HttpEntity<ApiResponse> editStudentGrade(long studentGradeId, int grade);
}
