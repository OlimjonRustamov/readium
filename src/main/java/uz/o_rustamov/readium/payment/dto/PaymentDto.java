package uz.o_rustamov.readium.payment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentDto {
    @NotNull(message = "student_id null bo'lmasligi kerak")
    long studentId;
    @NotNull(message = "group_id null bo'lmasligi kerak")
    long groupId;
    @NotNull(message = "date null bo'lmasligi kerak")
    String date;
    @NotNull(message = "sum null bo'lmasligi kerak")
    int sum;
    @NotNull(message = "month null bo'lmasligi kerak")
    String month;
}
