package uz.o_rustamov.readium.user.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.model.UserDto;

public interface UserService {

    HttpEntity<ApiResponse> getUsers(int page, int size);

    HttpEntity<ApiResponse> getUser(long id);

    HttpEntity<ApiResponse> addUser(UserDto dto);

    HttpEntity<ApiResponse> editUser(long id, UserDto dto);

    HttpEntity<ApiResponse> deleteUser(long id);

    HttpEntity<ApiResponse> changePassword(long id, String newPassword);

}
