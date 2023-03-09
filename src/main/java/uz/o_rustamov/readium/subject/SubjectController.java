package uz.o_rustamov.readium.subject;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.subject.dto.SubjectDto;
import uz.o_rustamov.readium.subject.service.SubjectServiceImpl;
import uz.o_rustamov.readium.user.model.User;

@Transactional
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    SubjectServiceImpl subjectService;

    public SubjectController(SubjectServiceImpl subjectService) {
        this.subjectService = subjectService;
    }

    @PreAuthorize("hasAuthority('VIEW_SUBJECTS')")
    @GetMapping()
    HttpEntity<?> getAllSubjectsOfStudyCentre(@CurrentUser User user) {
        return subjectService.getSubjectsOfStudyCentre(user);
    }

    @PreAuthorize("hasAuthority('VIEW_SUBJECTS')")
    @GetMapping("/{id}")
    HttpEntity<?> getAllSubjectsOfStudyCentre(@CurrentUser User user, @PathVariable long id) {
        return subjectService.getSingleSubject(user, id);
    }

    @PreAuthorize("hasAuthority('ADD_SUBJECTS')")
    @PostMapping()
    HttpEntity<?> addSubject(@CurrentUser User user, @RequestBody SubjectDto dto) {
        return subjectService.addSubject(user, dto);
    }

    @PreAuthorize("hasAuthority('EDIT_SUBJECTS')")
    @PutMapping("/{id}")
    HttpEntity<?> editSubject(@RequestBody SubjectDto dto, @PathVariable long id) {
        return subjectService.editSubject(id, dto);
    }

    @PreAuthorize("hasAuthority('DELETE_SUBJECTS')")
    @DeleteMapping("/{id}")
    HttpEntity<?> deleteSubject(@PathVariable long id) {
        return subjectService.deleteSubject(id);
    }
}
