package uz.o_rustamov.readium.profile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FirebaseTokenDto {

    @JsonProperty(value = "firebase_token")
    @NotNull(message = "firebase_token bo'sh bo'lishi mumkin emas")
    String firebaseToken;
}
