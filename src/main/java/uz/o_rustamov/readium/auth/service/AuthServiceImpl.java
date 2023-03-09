package uz.o_rustamov.readium.auth.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.auth.model.LoginDto;
import uz.o_rustamov.readium.configuration.JwtProvider;
import uz.o_rustamov.readium.role.repository.RoleRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtProvider jwtProvider;

    public AuthServiceImpl(JwtProvider jwtProvider, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public HttpEntity<ApiResponse> login(LoginDto dto) {
        Optional<User> optionalUser = userRepository.findByUsername(dto.getUsername());
        if (!optionalUser.isPresent())
            return ResponseEntity.status(404).body(new ApiResponse(
                    "Username mavjud emas",
                    404, null));
        User user = optionalUser.get();
        boolean equal = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if (equal) {
            String token = jwtProvider.generateToken(dto.getUsername());
            Map<String, String> map = new HashMap<>();
            map.put("api_token", token);
            map.put("role_name", user.getRole().getName());
            return ResponseEntity.ok(new ApiResponse(null, 200, map));
        }
        return ResponseEntity.status(409).body(
                new ApiResponse("Parol xato", 409, null));
    }
}
