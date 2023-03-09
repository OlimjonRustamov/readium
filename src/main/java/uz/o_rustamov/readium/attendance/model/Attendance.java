package uz.o_rustamov.readium.attendance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @ManyToOne
    User student;

    @ManyToOne
    Group group;

    @Column(nullable = false)
    java.sql.Date date;

    @Column(nullable = false)
    @JsonProperty("is_present")
    boolean isPresent;
}
