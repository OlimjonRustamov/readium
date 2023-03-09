package uz.o_rustamov.readium.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.readium.group.model.Group;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @Column(nullable = false)
    private String name;

    @JsonProperty("book_pages")
    private String bookPages;

    private java.sql.Date deadline;

    @ManyToOne
    Group group;

    @JsonProperty("is_active")
    @Column(nullable = false)
    private boolean isActive;

}
