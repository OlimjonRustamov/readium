package uz.o_rustamov.readium.assignment;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.assignment.dto.AssignmentDto;
import uz.o_rustamov.readium.assignment.service.AssignmentServiceImpl;

import javax.validation.Valid;

@RestController
@Transactional
@RequestMapping("/api/assignment")
public class AssignmentController {

    AssignmentServiceImpl assignmentService;

    public AssignmentController(AssignmentServiceImpl assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/my-assignments")
    HttpEntity<?> getMyAssignments(@RequestParam long group_id) {
        return assignmentService.getMyAssignments(group_id);
    }

    @GetMapping("/my-active-assignments")
    HttpEntity<?> getMyAssignments(@RequestParam long group_id, @RequestParam boolean is_active) {
        return assignmentService.getMyActiveAssignments(group_id, is_active);
    }

    @PreAuthorize("hasAuthority('ADD_ASSIGNMENT')")
    @PostMapping("/add")
    HttpEntity<?> addAssignment(@Valid @RequestBody AssignmentDto dto) {
        return assignmentService.addAssignment(dto);
    }

    @PreAuthorize("hasAuthority('EDIT_ASSIGNMENT')")
    @PostMapping("/mark-as-outdated")
    HttpEntity<?> addAssignment(@RequestParam long assignment_id) {
        return assignmentService.makeAssignmentOutdated(assignment_id);
    }
}
