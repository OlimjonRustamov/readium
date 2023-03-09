package uz.o_rustamov.readium.auth.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotNull(message = "Username bo'sh bo'lishi mumkin emas")
    String username;
    @NotNull(message = "Parol bo'sh bo'lishi mumkin emas")
    String password;

}
