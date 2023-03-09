package uz.o_rustamov.readium.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.o_rustamov.readium.enums.Permission;
import uz.o_rustamov.readium.files.model.Attachment;
import uz.o_rustamov.readium.role.model.Role;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonIgnore
    private long createdAt = new Date().getTime();

    @Column(nullable = false)
    @JsonIgnore
    private long updatedAt = new Date().getTime();

    @Column(nullable = false)
    @JsonProperty("full_name")
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonProperty("phone_number")
    private String phoneNumber;

    @Column
    @JsonIgnore
    private String fcmToken;

    @ManyToOne
    Role role;

    @OneToOne
    Attachment attachment;

    @ManyToOne
    @JsonProperty("study_centre")
    StudyCentre studyCentre;

    @JsonIgnore
    boolean enabled;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<Permission> permissionList = role.getPermissionList();
        for (Permission permission : permissionList) {
            grantedAuthorityList.add((GrantedAuthority) permission::name);
        }
        return grantedAuthorityList;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
