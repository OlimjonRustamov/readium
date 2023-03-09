package uz.o_rustamov.readium.study_centre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudyCentreDto {

    @NotNull(message = "name bo'sh bo'lishi mumkin emas")
    String name;

    @NotNull(message = "address bo'sh bo'lishi mumkin emas")
    String address;

    @NotNull(message = "Telefon raqam bo'sh bo'lishi mumkin emas")
    @JsonProperty("phone_number")
    String phoneNumber;
}
