package uz.o_rustamov.readium.profile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateInfoDto {

    @JsonProperty(value = "full_name")
    @NotNull(message = "fullName bo'sh bo'lishi mumkin emas")
    String fullName;

    @NotNull(message = "username bo'sh bo'lishi mumkin emas")
    String username;

    @JsonProperty(value = "phone_number")
    @NotNull(message = "phoneNumber bo'sh bo'lishi mumkin emas")
    String phoneNumber;
}
