package uz.o_rustamov.readium.study_centre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class StudyCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @Column(nullable = false)
    @JsonIgnore
    private long updatedAt = new Date().getTime();

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @JsonProperty("phone_number")
    private String phoneNumber;
}
