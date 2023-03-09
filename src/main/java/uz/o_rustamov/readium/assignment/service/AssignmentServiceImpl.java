package uz.o_rustamov.readium.assignment.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.assignment.dto.AssignmentDto;
import uz.o_rustamov.readium.assignment.model.Assignment;
import uz.o_rustamov.readium.assignment.repository.AssignmentRepository;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.group.repository.GroupRepository;

import java.sql.Date;
import java.util.Optional;

import static uz.o_rustamov.readium.Constants.NOT_FOUND;
import static uz.o_rustamov.readium.Constants.SUCCESS;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    GroupRepository groupRepository;
    AssignmentRepository assignmentRepository;

    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, GroupRepository groupRepository) {
        this.assignmentRepository = assignmentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getMyAssignments(long groupId) {
        return ResponseEntity.ok(new ApiResponse(null, 200, assignmentRepository.findAllByGroup_Id(groupId)));
    }

    @Override
    public HttpEntity<ApiResponse> getMyActiveAssignments(long groupId, boolean isActive) {
        return ResponseEntity.ok(new ApiResponse(null, 200, assignmentRepository.findAllByGroup_IdAndActive(groupId, isActive)));
    }

    @Override
    public HttpEntity<ApiResponse> addAssignment(AssignmentDto dto) {
        Optional<Group> optionalGroup = groupRepository.findById(dto.getGroupId());
        if (!optionalGroup.isPresent()) return NOT_FOUND;
        Group group = optionalGroup.get();
        Assignment assignment = new Assignment();
        assignment.setActive(true);
        assignment.setBookPages(dto.getBookPages());
        assignment.setDeadline(new Date(dto.getDeadline()));
        assignment.setGroup(group);
        assignmentRepository.save(assignment);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> makeAssignmentOutdated(long assignmentId) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);
        if (!optionalAssignment.isPresent()) return NOT_FOUND;
        Assignment assignment = optionalAssignment.get();
        assignment.setActive(false);
        assignmentRepository.save(assignment);
        return SUCCESS;
    }
}
