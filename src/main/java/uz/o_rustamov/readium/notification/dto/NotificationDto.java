package uz.o_rustamov.readium.notification.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NotificationDto {

    @NotNull(message = "title bo'sh bo'lishi mumkin emas")
    String title;

    @NotNull(message = "description bo'sh bo'lishi mumkin emas")
    String description;

    @JsonProperty("user_id")
    @NotNull(message = "userId bo'sh bo'lishi mumkin emas")
    long userId;
}
