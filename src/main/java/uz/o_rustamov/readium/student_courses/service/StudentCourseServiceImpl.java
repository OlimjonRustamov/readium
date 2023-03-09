package uz.o_rustamov.readium.student_courses.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.group.repository.GroupRepository;
import uz.o_rustamov.readium.student_courses.model.StudentCourse;
import uz.o_rustamov.readium.student_courses.repository.StudentCourseRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.NOT_FOUND;
import static uz.o_rustamov.readium.Constants.SUCCESS;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    UserRepository userRepository;
    GroupRepository groupRepository;
    StudentCourseRepository studentCourseRepository;

    public StudentCourseServiceImpl(UserRepository userRepository, GroupRepository groupRepository, StudentCourseRepository studentCourseRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getMyCourses(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, studentCourseRepository.findAllByStudent_Id(user.getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getUserCourses(long studentId) {
        return ResponseEntity.ok(new ApiResponse(null, 200, studentCourseRepository.findAllByStudent_Id(studentId)));
    }

    @Override
    public HttpEntity<ApiResponse> removeStudentFromGroup(long userId, long groupId) {
        try {
            studentCourseRepository.delete(studentCourseRepository.findByCourse_IdAndStudent_Id(groupId, userId));
            return SUCCESS;
        } catch (Exception exception) {
            return ResponseEntity.status(406).body(new ApiResponse(exception.getMessage(), 405, null));
        }
    }

    @Override
    public HttpEntity<ApiResponse> attachStudentToGroup(long userid, long groupId) {
        Optional<User> optionalUser = userRepository.findById(userid);
        if(!optionalUser.isPresent()) return NOT_FOUND;
        User user = optionalUser.get();

        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if(!optionalGroup.isPresent()) return NOT_FOUND;
        Group group = optionalGroup.get();

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudent(user);
        studentCourse.setCourse(group);
        studentCourseRepository.save(studentCourse);
        return SUCCESS;
    }
}
