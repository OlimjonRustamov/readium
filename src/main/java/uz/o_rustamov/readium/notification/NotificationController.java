package uz.o_rustamov.readium.notification;

import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.notification.service.NotificationServiceImpl;
import uz.o_rustamov.readium.user.model.User;

@Transactional
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    NotificationServiceImpl notificationService;

    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping()
    public HttpEntity<?> getMyNotifications(@CurrentUser User user, @RequestParam("page") int page, @RequestParam("size") int size) {
        return notificationService.getMyNotifications(user, page, size);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getSingleNotification(@CurrentUser User user, @PathVariable long id) {
        return notificationService.getSingleNotification(user, id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteNotification(@CurrentUser User user, @PathVariable long id) {
        return notificationService.deleteNotification(id);
    }
}
