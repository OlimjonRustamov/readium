package uz.o_rustamov.readium.profile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordDto {

    @JsonProperty("new_password")
    @NotNull(message = "newPassword bo'sh bo'lishi mumkin emas")
    String newPassword;

    @JsonProperty("old_password")
    @NotNull(message = "oldPassword bo'sh bo'lishi mumkin emas")
    String oldPassword;
}
