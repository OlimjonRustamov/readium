package uz.o_rustamov.readium.student_courses.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.group.dto.GroupDto;
import uz.o_rustamov.readium.user.model.User;

public interface StudentCourseService {

    HttpEntity<ApiResponse> getMyCourses(User user);

    HttpEntity<ApiResponse> getUserCourses(long studentId);

    HttpEntity<ApiResponse> removeStudentFromGroup(long userId, long groupId);

    HttpEntity<ApiResponse> attachStudentToGroup(long userid, long groupId);

}
