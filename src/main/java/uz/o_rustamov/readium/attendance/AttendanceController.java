package uz.o_rustamov.readium.attendance;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.attendance.service.AttendanceServiceImpl;
import uz.o_rustamov.readium.user.model.User;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    AttendanceServiceImpl attendanceService;

    public AttendanceController(AttendanceServiceImpl attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/my")
    HttpEntity<?> getMyAttendances(@CurrentUser User user, @RequestParam long group_id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return attendanceService.getMyAttendances(user, group_id, page, size);
    }

    @PreAuthorize("hasAuthority('VIEW_ATTENDANCES')")
    @GetMapping("/user")
    HttpEntity<?> getUserAttendances(@RequestParam long user_id, @RequestParam long group_id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return attendanceService.getUserAttendances(user_id, group_id, page, size);
    }

    @PreAuthorize("hasAuthority('ADD_ATTENDANCE')")
    @PostMapping("/create")
    HttpEntity<?> createAttendance(@RequestParam long user_id, @RequestParam long group_id, @RequestParam long date, @RequestParam boolean is_present) {
        return attendanceService.createAttendance(user_id, group_id, date, is_present);
    }

    @PreAuthorize("hasAuthority('EDIT_ATTENDANCE')")
    @PutMapping("/edit")
    HttpEntity<?> editAttendance(@RequestParam long attendance_id, @RequestParam boolean is_present) {
        return attendanceService.editAttendance(attendance_id, is_present);
    }
}
