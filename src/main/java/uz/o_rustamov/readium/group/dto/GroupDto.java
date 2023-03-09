package uz.o_rustamov.readium.group.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GroupDto {

    @NotNull(message = "name bo'sh bo'lishi mumkin emas")
    String name;

    @JsonProperty("subject_id")
    @NotNull(message = "subject_id bo'sh bo'lishi mumkin emas")
    long subjectId;

    @JsonProperty("study_centre_id")
    @NotNull(message = "study_centre_id bo'sh bo'lishi mumkin emas")
    long studyCentreId;

    @JsonProperty("teacher_name")
    @NotNull(message = "teacher_name bo'sh bo'lishi mumkin emas")
    String teacherName;
}
