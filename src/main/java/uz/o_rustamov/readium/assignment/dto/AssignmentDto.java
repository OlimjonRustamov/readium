package uz.o_rustamov.readium.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssignmentDto {

    @NotNull(message = "Name must not be null")
    String name;

    @JsonProperty("book_pages")
    String bookPages;

    @NotNull(message = "deadline must not be null")
    int deadline;

    @NotNull(message = "group_id must not be null")
    @JsonProperty("group_id")
    long groupId;
}
