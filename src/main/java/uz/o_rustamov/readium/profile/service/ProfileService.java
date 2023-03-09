package uz.o_rustamov.readium.profile.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.auth.model.LoginDto;
import uz.o_rustamov.readium.profile.model.ChangePasswordDto;
import uz.o_rustamov.readium.profile.model.FirebaseTokenDto;
import uz.o_rustamov.readium.profile.model.UpdateInfoDto;
import uz.o_rustamov.readium.user.model.User;

public interface ProfileService {

    HttpEntity<ApiResponse> getMe(User user);

    HttpEntity<ApiResponse> changePassword(User user, ChangePasswordDto dto);

    HttpEntity<ApiResponse> updateInfo(User user, UpdateInfoDto dto);

    HttpEntity<ApiResponse> setFirebaseToken(User user, FirebaseTokenDto dto);


    HttpEntity<ApiResponse> setProfilePhoto(User user, long attachmentId);

}
