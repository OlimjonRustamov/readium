package uz.o_rustamov.readium.student_grades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.student_grades.service.StudentGradeServiceImpl;
import uz.o_rustamov.readium.user.model.User;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/grade")
public class StudentGradeController {

    StudentGradeServiceImpl studentGradeService;

    public StudentGradeController(StudentGradeServiceImpl studentGradeService) {
        this.studentGradeService = studentGradeService;
    }

    @GetMapping
    HttpEntity<?> getMyGrades(@CurrentUser User user, @RequestParam("page") int page, @RequestParam("size") int size) {
        return studentGradeService.getMyGrades(user, page, size);
    }

    @PreAuthorize("hasAuthority('VIEW_GRADES')")
    @GetMapping("/user/{userId}")
    HttpEntity<?> getUserGrades(@PathVariable long userId, @RequestParam("page") int page, @RequestParam("size") int size) {
        return studentGradeService.getUserGrades(userId,page, size);
    }

    @PreAuthorize("hasAuthority('ASSESS_STUDENT')")
    @PostMapping("/add")
    HttpEntity<?> addStudentGrade(@RequestParam long student_id, @RequestParam long assignment_id, @RequestParam int grade) {
        return studentGradeService.assessStudent(student_id, assignment_id, grade);
    }

    @PreAuthorize("hasAuthority('ASSESS_STUDENT')")
    @PutMapping("/edit")
    HttpEntity<?> editStudentGrade(@RequestParam long grade_id, @RequestParam int grade) {
        return studentGradeService.editStudentGrade(grade_id, grade);
    }
}
