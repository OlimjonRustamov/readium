package uz.o_rustamov.readium.assignment.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.assignment.dto.AssignmentDto;
import uz.o_rustamov.readium.user.model.User;

public interface AssignmentService {

    HttpEntity<ApiResponse> getMyAssignments(long groupId);

    HttpEntity<ApiResponse> getMyActiveAssignments(long groupId, boolean isActive);

    HttpEntity<ApiResponse> addAssignment(AssignmentDto dto);

    HttpEntity<ApiResponse> makeAssignmentOutdated(long assignmentId);
}
