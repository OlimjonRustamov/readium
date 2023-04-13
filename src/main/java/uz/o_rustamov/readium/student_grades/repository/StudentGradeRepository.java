package uz.o_rustamov.readium.student_grades.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.readium.student_grades.model.StudentGrade;

import java.util.List;

@Repository
public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {

    Page<StudentGrade> findAllByStudent_Id(Long student_id, Pageable pageable);

}
