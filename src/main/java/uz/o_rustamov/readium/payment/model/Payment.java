package uz.o_rustamov.readium.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();
    
    @ManyToOne
    private User student;

    @ManyToOne
    private Group group;

    @Column(nullable = false)
    private long date = new Date().getTime();

    @Column
    private int sum;

    @Column
    private String month;

}
