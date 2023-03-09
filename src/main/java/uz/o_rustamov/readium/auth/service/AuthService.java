package uz.o_rustamov.readium.auth.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.auth.model.LoginDto;

public interface AuthService {

    HttpEntity<ApiResponse> login(LoginDto dto);

}
