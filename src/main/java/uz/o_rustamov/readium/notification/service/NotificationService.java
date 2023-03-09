package uz.o_rustamov.readium.notification.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.notification.dto.NotificationDto;
import uz.o_rustamov.readium.user.model.User;

public interface NotificationService {

    HttpEntity<ApiResponse> getMyNotifications(User user);

    HttpEntity<ApiResponse> getSingleNotification(User user, long id);

    HttpEntity<ApiResponse> deleteNotification(long id);

    HttpEntity<ApiResponse> markAsRead(User user, long id);

}
