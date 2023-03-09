package uz.o_rustamov.readium.news_events.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewsDto {

    @NotNull(message = "title bo'sh bo'lishi mumkin emas")
    String title;

    @NotNull(message = "description bo'sh bo'lishi mumkin emas")
    String description;

    @JsonProperty("study_centre_id")
    @NotNull(message = "study_centre_id bo'sh bo'lishi mumkin emas")
    long studyCentreId;

    @NotNull(message = "date bo'sh bo'lishi mumkin emas")
    String date; // template DD.MM.YYYY

    @JsonProperty("is_active")
    @NotNull(message = "is_active bo'sh bo'lishi mumkin emas")
    boolean isActive; // template DD.MM.YYYY
}
