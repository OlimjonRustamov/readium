package uz.o_rustamov.readium.profile.controller;

import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.profile.model.ChangePasswordDto;
import uz.o_rustamov.readium.profile.model.FirebaseTokenDto;
import uz.o_rustamov.readium.profile.model.UpdateInfoDto;
import uz.o_rustamov.readium.profile.service.ProfileServiceImpl;
import uz.o_rustamov.readium.user.model.User;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    ProfileServiceImpl profileService;

    public ProfileController(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/get-me")
    public HttpEntity<?> getMe(@CurrentUser User user) {
        return profileService.getMe(user);
    }

    @PostMapping("/change-password")
    public HttpEntity<?> changePassword(@CurrentUser User user, @Valid @RequestBody ChangePasswordDto dto) {
        return profileService.changePassword(user, dto);
    }

    @PostMapping("/update-info")
    public HttpEntity<?> updateInfo(@CurrentUser User user, @Valid @RequestBody UpdateInfoDto dto) {
        return profileService.updateInfo(user, dto);
    }

    @PostMapping("/set-firebase-token")
    public HttpEntity<?> setFcmToken(@CurrentUser User user, @Valid @RequestBody FirebaseTokenDto dto) {
        return profileService.setFirebaseToken(user, dto);
    }

    @PostMapping("/set-profile-photo")
    public HttpEntity<?> setProfilePhoto(@CurrentUser User user, @RequestParam("attachment_id") long attachmentId) {
        return profileService.setProfilePhoto(user, attachmentId);
    }
}
