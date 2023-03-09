package uz.o_rustamov.readium.student_courses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.subject.model.Subject;
import uz.o_rustamov.readium.user.model.User;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @ManyToOne
    @JsonProperty("student_id")
    User student;

    @ManyToOne
    Group course;
}
