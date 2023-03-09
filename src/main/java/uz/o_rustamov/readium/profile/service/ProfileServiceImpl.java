package uz.o_rustamov.readium.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.files.model.Attachment;
import uz.o_rustamov.readium.files.repository.AttachmentRepository;
import uz.o_rustamov.readium.profile.model.ChangePasswordDto;
import uz.o_rustamov.readium.profile.model.FirebaseTokenDto;
import uz.o_rustamov.readium.profile.model.UpdateInfoDto;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class ProfileServiceImpl implements ProfileService {

    UserRepository userRepository;
    AttachmentRepository attachmentRepository;
    PasswordEncoder passwordEncoder;


    public ProfileServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AttachmentRepository attachmentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getMe(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, user));
    }

    @Override
    public HttpEntity<ApiResponse> changePassword(User user, ChangePasswordDto dto) {
        if (passwordEncoder.encode(dto.getOldPassword()).equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);
            return SUCCESS;
        } else return ResponseEntity.status(400).body(
                new ApiResponse("Eski parol noto'g'ri kiritilgan", 400, null));
    }

    @Override
    public HttpEntity<ApiResponse> updateInfo(User user, UpdateInfoDto dto) {
        if (!userRepository.existsByUsername(dto.getUsername())) {
            user.setFullName(dto.getFullName());
            user.setPhoneNumber(dto.getPhoneNumber());
            user.setUsername(dto.getUsername());
            userRepository.save(user);
            return SUCCESS;
        } else return ALREADY_EXIST;
    }

    @Override
    public HttpEntity<ApiResponse> setFirebaseToken(User user, FirebaseTokenDto dto) {
        user.setFcmToken(dto.getFirebaseToken());
        userRepository.save(user);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> setProfilePhoto(User user, long attachmentId) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if(optionalAttachment.isPresent()) return NOT_FOUND;
        Attachment attachment = optionalAttachment.get();
        user.setAttachment(attachment);

        userRepository.save(user);
        return SUCCESS;
    }

}
