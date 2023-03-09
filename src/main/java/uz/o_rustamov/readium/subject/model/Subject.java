package uz.o_rustamov.readium.subject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    StudyCentre studyCentre;

}
