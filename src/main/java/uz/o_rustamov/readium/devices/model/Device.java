package uz.o_rustamov.readium.devices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import uz.o_rustamov.readium.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();


    @JsonProperty("device_name")
    @Column(nullable = false)
    private String deviceName;

    @ManyToOne
    User user;

    @JsonProperty("last_active_date")
    @Column(nullable = false)
    private java.sql.Date lastActiveDate;

    @Column(nullable = false)
    @JsonProperty("is_reliable")
    private boolean isReliable;
}
