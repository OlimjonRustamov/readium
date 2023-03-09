package uz.o_rustamov.readium.subject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SubjectDto {

    @NotNull(message = "title bo'sh bo'lishi mumkin emas")
    String name;
}
