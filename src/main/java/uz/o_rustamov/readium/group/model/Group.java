package uz.o_rustamov.readium.group.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.subject.model.Subject;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "course")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @Column(nullable = false)
    private String name;

    @ManyToOne
    Subject subject;

    @ManyToOne
    StudyCentre studyCentre;


    @JsonProperty("teacher_name")
    @Column(nullable = false)
    private String teacherName;

    @JsonProperty("is_active")
    @Column(nullable = false)
    private boolean isActive;
}
