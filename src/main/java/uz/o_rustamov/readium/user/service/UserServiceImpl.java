package uz.o_rustamov.readium.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.files.model.Attachment;
import uz.o_rustamov.readium.files.repository.AttachmentRepository;
import uz.o_rustamov.readium.role.model.Role;
import uz.o_rustamov.readium.role.repository.RoleRepository;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.study_centre.repository.StudyCentreRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.model.UserDto;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    AttachmentRepository attachmentRepository;
    StudyCentreRepository studyCentreRepository;
    PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, StudyCentreRepository studyCentreRepository, AttachmentRepository attachmentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.studyCentreRepository = studyCentreRepository;
        this.attachmentRepository = attachmentRepository;
    }


    @Override
    public HttpEntity<ApiResponse> getUsers(int page, int size) {
        return ResponseEntity.ok(new ApiResponse(null, 200, userRepository.findAll(PageRequest.of(page, size))));
    }

    @Override
    public HttpEntity<ApiResponse> getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return NOT_FOUND;
        User user = optionalUser.get();
        return ResponseEntity.ok(new ApiResponse(null, 200, user));
    }

    @Override
    public HttpEntity<ApiResponse> addUser(UserDto dto) {
        Optional<Role> optionalRole = roleRepository.findById(dto.getRoleId());
        if (!optionalRole.isPresent()) return NOT_FOUND;
        if (userRepository.existsByUsername(dto.getUsername())) return ALREADY_EXIST;
        Role role = optionalRole.get();

        Optional<StudyCentre> optionalStudyCentre = studyCentreRepository.findById(dto.getStudyCentreId());
        if (!optionalStudyCentre.isPresent()) return NOT_FOUND;

        StudyCentre studyCentre = optionalStudyCentre.get();
        User user = new User();
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEnabled(true);
        user.setFullName(dto.getFullName());
        user.setRole(role);
        user.setStudyCentre(studyCentre);
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> editUser(long id, UserDto dto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return NOT_FOUND;
        if (userRepository.existsByUsername(dto.getUsername())) return ALREADY_EXIST;
        User user = optionalUser.get();
        user.setUsername(dto.getUsername());
        user.setFullName(dto.getFullName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user = userRepository.save(user);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteUser(long id) {
        try {
            userRepository.deleteById(id);
            return SUCCESS;
        } catch (DataIntegrityViolationException ex) {
            return CONSTRAINT_EXCEPTION;
        } catch (Exception e) {
            return NOT_FOUND;
        }
    }

    @Override
    public HttpEntity<ApiResponse> changePassword(long id, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return NOT_FOUND;
        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        user = userRepository.save(user);
        return SUCCESS;
    }


}
