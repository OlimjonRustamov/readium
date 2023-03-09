package uz.o_rustamov.readium.study_centre;


import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.study_centre.model.StudyCentreDto;
import uz.o_rustamov.readium.study_centre.service.StudyCentreServiceImpl;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/study-centre")
public class StudyCentreController {

    StudyCentreServiceImpl studyCentreService;

    public StudyCentreController(StudyCentreServiceImpl studyCentreService) {
        this.studyCentreService = studyCentreService;
    }

    @PreAuthorize(value = "hasAuthority('VIEW_STUDY_CENTRES')")
    @GetMapping
    public HttpEntity<ApiResponse> getStudyCentres(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studyCentreService.getAllStudyCentres(page, size);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_STUDY_CENTRES')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getStudyCentreById(@PathVariable long id) {
        return studyCentreService.getSingleStudyCentre(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_STUDY_CENTRE')")
    @PostMapping
    public HttpEntity<ApiResponse> addStudyCentre(@Valid @RequestBody StudyCentreDto dto) {
        return studyCentreService.addStudyCentre(dto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_STUDY_CENTRE')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editStudyCentre(@Valid @RequestBody StudyCentreDto dto, @PathVariable long id) {
        return studyCentreService.editStudyCentre(id, dto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_STUDY_CENTRE')")
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteStudyCentre(@PathVariable long id) {
        return studyCentreService.deleteStudyCentre(id);
    }
}
