package uz.o_rustamov.readium.auth.controller;

import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.auth.model.LoginDto;
import uz.o_rustamov.readium.auth.service.AuthServiceImpl;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public HttpEntity<ApiResponse> login(@Valid @RequestBody LoginDto dto) {
        return authService.login(dto);
    }

}
