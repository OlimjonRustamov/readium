package uz.o_rustamov.readium.student_courses.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.readium.student_courses.model.StudentCourse;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

    List<StudentCourse> findAllByStudent_Id(Long student_id, Pageable pageable);

    StudentCourse findByCourse_IdAndStudent_Id(Long course_id, Long student_id);
}
