package uz.o_rustamov.readium.student_grades.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.assignment.model.Assignment;
import uz.o_rustamov.readium.assignment.repository.AssignmentRepository;
import uz.o_rustamov.readium.student_grades.model.StudentGrade;
import uz.o_rustamov.readium.student_grades.repository.StudentGradeRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.NOT_FOUND;
import static uz.o_rustamov.readium.Constants.SUCCESS;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {

    StudentGradeRepository studentGradeRepository;
    UserRepository userRepository;
    AssignmentRepository assignmentRepository;


    public StudentGradeServiceImpl(StudentGradeRepository studentGradeRepository, UserRepository userRepository, AssignmentRepository assignmentRepository) {
        this.studentGradeRepository = studentGradeRepository;
        this.userRepository = userRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getMyGrades(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, studentGradeRepository.findAllByStudent_Id(user.getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getUserGrades(long studentId) {
        return ResponseEntity.ok(new ApiResponse(null, 200, studentGradeRepository.findAllByStudent_Id(studentId)));
    }

    @Override
    public HttpEntity<ApiResponse> assessStudent(long studentId, long assignmentId, int grade) {
        Optional<User> optionalUser = userRepository.findById(studentId);
        if(!optionalUser.isPresent()) return NOT_FOUND;
        User student = optionalUser.get();

        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);
        if(!optionalAssignment.isPresent()) return NOT_FOUND;
        Assignment assignment = optionalAssignment.get();

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudent(student);
        studentGrade.setAssignment(assignment);
        studentGrade.setGrade(grade);
        studentGradeRepository.save(studentGrade);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> editStudentGrade(long studentGradeId, int grade) {
        Optional<StudentGrade> optionalStudentGrade = studentGradeRepository.findById(studentGradeId);
        if(!optionalStudentGrade.isPresent()) return NOT_FOUND;
        StudentGrade studentGrade = optionalStudentGrade.get();

        studentGrade.setGrade(grade);
        studentGradeRepository.save(studentGrade);
        return SUCCESS;
    }
}
