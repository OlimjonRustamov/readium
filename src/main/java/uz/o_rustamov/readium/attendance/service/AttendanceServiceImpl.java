package uz.o_rustamov.readium.attendance.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.attendance.model.Attendance;
import uz.o_rustamov.readium.attendance.repository.AttendanceRepository;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.group.repository.GroupRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.sql.Date;
import java.util.Optional;

import static uz.o_rustamov.readium.Constants.NOT_FOUND;
import static uz.o_rustamov.readium.Constants.SUCCESS;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    UserRepository userRepository;
    GroupRepository groupRepository;
    AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.attendanceRepository = attendanceRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }


    @Override
    public HttpEntity<ApiResponse> getMyAttendances(User user, long courseId, int page, int size) {
        return ResponseEntity.ok(new ApiResponse(null, 200, attendanceRepository.findAllByStudent_IdAndGroup_Id(user.getId(), courseId, PageRequest.of(page, size))));
    }

    @Override
    public HttpEntity<ApiResponse> getUserAttendances(long userId, long courseId, int page, int size) {
        return ResponseEntity.ok(new ApiResponse(null, 200, attendanceRepository.findAllByStudent_IdAndGroup_Id(userId, courseId, PageRequest.of(page, size))));
    }

    @Override
    public HttpEntity<ApiResponse> createAttendance(long studentId, long courseId, long date, boolean isPresent) {
        Optional<User> optionalUser = userRepository.findById(studentId);
        if(!optionalUser.isPresent()) return NOT_FOUND;
        User student = optionalUser.get();

        Optional<Group> optionalGroup = groupRepository.findById(courseId);
        if(!optionalGroup.isPresent()) return NOT_FOUND;
        Group course = optionalGroup.get();

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setGroup(course);
        attendance.setDate(new Date(date));
        attendance.setPresent(isPresent);
        attendanceRepository.save(attendance);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> editAttendance(long attendanceId, boolean isPresent) {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendanceId);
        if(!optionalAttendance.isPresent()) return NOT_FOUND;
        Attendance attendance = optionalAttendance.get();

        attendance.setPresent(isPresent);
        attendanceRepository.save(attendance);
        return SUCCESS;
    }
}
