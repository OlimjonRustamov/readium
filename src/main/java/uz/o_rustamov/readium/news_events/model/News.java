package uz.o_rustamov.readium.news_events.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private java.sql.Date date;

    @ManyToOne
    StudyCentre studyCentre;

    @Column(nullable = false)
    @JsonProperty("is_active")
    private boolean isActive;
}
