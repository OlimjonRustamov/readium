package uz.o_rustamov.readium.student_grades.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import uz.o_rustamov.readium.assignment.model.Assignment;
import uz.o_rustamov.readium.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class StudentGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @ManyToOne
    User student;

    @ManyToOne
    Assignment assignment;

    @Column(nullable = false)
    int grade;
}
