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
    HttpEntity<?> getAllSubjectsOfStudyCentre(@CurrentUser User user,
                                              @RequestParam("page") int page, @RequestParam("size") int size) {
        return subjectService.getSubjectsOfStudyCentre(user, page, size);
    }

    @PreAuthorize("hasAuthority('VIEW_SUBJECTS')")
    @GetMapping("/{id}")
    HttpEntity<?> getSubjectOfStudyCentre(@CurrentUser User user, @PathVariable long id) {
        return subjectService.getSingleSubject(user, id);
    }

    @PreAuthorize("hasAuthority('ADD_SUBJECT')")
    @PostMapping()
    HttpEntity<?> addSubject(@CurrentUser User user, @RequestBody SubjectDto dto) {
        return subjectService.addSubject(user, dto);
    }

    @PreAuthorize("hasAuthority('EDIT_SUBJECT')")
    @PutMapping("/{id}")
    HttpEntity<?> editSubject(@RequestBody SubjectDto dto, @PathVariable long id) {
        return subjectService.editSubject(id, dto);
    }

    @PreAuthorize("hasAuthority('DELETE_SUBJECT')")
    @DeleteMapping("/{id}")
    HttpEntity<?> deleteSubject(@PathVariable long id) {
        return subjectService.deleteSubject(id);
    }
}
