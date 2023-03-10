package uz.o_rustamov.readium.notification.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.notification.dto.NotificationDto;
import uz.o_rustamov.readium.notification.model.Notification;
import uz.o_rustamov.readium.notification.repository.NotificationRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;
    UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public HttpEntity<ApiResponse> getMyNotifications(User user, int page, int size) {
        return ResponseEntity.ok(new ApiResponse(null, 200, notificationRepository.findByUser_Id(user.getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getSingleNotification(User user, long id) {
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isPresent()) {
            if (user.getId().equals(optionalNotification.get().getId())) {
                Notification notification = optionalNotification.get();
                notification.setRead(true);
                notificationRepository.save(notification);
                return ResponseEntity.ok(new ApiResponse(null, 200, notification));
            } else return FORBIDDEN_EXCEPTION;
        } else return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> deleteNotification(long id) {
        try {
            notificationRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception ex) {
            return ResponseEntity.status(406).body(new ApiResponse(ex.getMessage(), 406, null));
        }
    }
}
