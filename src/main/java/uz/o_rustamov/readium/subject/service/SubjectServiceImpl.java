package uz.o_rustamov.readium.subject.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.study_centre.repository.StudyCentreRepository;
import uz.o_rustamov.readium.subject.dto.SubjectDto;
import uz.o_rustamov.readium.subject.model.Subject;
import uz.o_rustamov.readium.subject.repository.SubjectRepository;
import uz.o_rustamov.readium.user.model.User;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class SubjectServiceImpl implements SubjectService {

    SubjectRepository subjectRepository;
    StudyCentreRepository studyCentreRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, StudyCentreRepository studyCentreRepository) {
        this.subjectRepository = subjectRepository;
        this.studyCentreRepository = studyCentreRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getSubjectsOfStudyCentre(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, subjectRepository.findByStudyCentre_Id(user.getStudyCentre().getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getSingleSubject(User user, long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent()) return NOT_FOUND;
        return ResponseEntity.ok(new ApiResponse(null, 200, optionalSubject.get()));
    }

    @Override
    public HttpEntity<ApiResponse> addSubject(User user, SubjectDto dto) {
        if (subjectRepository.existsByName(dto.getName())) {
            return ALREADY_EXIST;
        }
        Optional<StudyCentre> optionalStudyCentre = studyCentreRepository.findById(user.getStudyCentre().getId());
        if (!optionalStudyCentre.isPresent()) {
            return NOT_FOUND;
        }
        StudyCentre studyCentre = optionalStudyCentre.get();
        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setStudyCentre(studyCentre);
        subjectRepository.save(subject);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> editSubject(long id, SubjectDto dto) {
        return ON_PROCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteSubject(long id) {
        try {
            subjectRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ApiResponse(ex.getMessage(), 400, null));
        }
    }
}
