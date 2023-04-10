package uz.o_rustamov.readium.notification.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.readium.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @Column(nullable = false)
    private long date = new Date().getTime();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @JsonProperty("is_read")
    private boolean isRead;

    @JsonIgnore
    @ManyToOne
    User user;
}
