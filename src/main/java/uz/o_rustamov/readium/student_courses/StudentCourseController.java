package uz.o_rustamov.readium.student_courses;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.student_courses.service.StudentCourseServiceImpl;
import uz.o_rustamov.readium.user.model.User;

@RestController
@RequestMapping("/api/student-course")
public class StudentCourseController {

    StudentCourseServiceImpl studentCourseService;

    public StudentCourseController(StudentCourseServiceImpl studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping
    HttpEntity<?> getMyCourses(@CurrentUser User user) {
        return studentCourseService.getMyCourses(user);
    }

    @PreAuthorize("hasAuthority('VIEW_STUDENT_COURSES')")
    @GetMapping("/{id}")
    HttpEntity<?> getUserCourses(@PathVariable long id) {
        return studentCourseService.getUserCourses(id);
    }

    @PreAuthorize("hasAuthority('ATTACH_STUDENT_TO_GROUP')")
    @PostMapping("/attach")
    HttpEntity<?> attachUserToGroup(@RequestParam long user_id, @RequestParam long group_id) {
        return studentCourseService.attachStudentToGroup(user_id, group_id);
    }

    @PreAuthorize("hasAuthority('REMOVE_STUDENT_FROM_GROUP')")
    @DeleteMapping("/remove")
    HttpEntity<?> removeUserFromGroup(@RequestParam long user_id, @RequestParam long group_id) {
        return studentCourseService.removeStudentFromGroup(user_id, group_id);
    }
}
