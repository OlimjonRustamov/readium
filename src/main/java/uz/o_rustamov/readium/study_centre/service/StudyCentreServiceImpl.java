package uz.o_rustamov.readium.study_centre.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.study_centre.model.StudyCentreDto;
import uz.o_rustamov.readium.study_centre.repository.StudyCentreRepository;

import java.util.Date;
import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class StudyCentreServiceImpl implements StudyCentreService {

    StudyCentreRepository studyCentreRepository;

    public StudyCentreServiceImpl(StudyCentreRepository studyCentreRepository) {
        this.studyCentreRepository = studyCentreRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getAllStudyCentres() {
        return ResponseEntity.ok(new ApiResponse(null, 200, studyCentreRepository.findAll()));
    }

    @Override
    public HttpEntity<ApiResponse> getSingleStudyCentre(long id) {
        Optional<StudyCentre> optionalStudyCentre = studyCentreRepository.findById(id);
        if (optionalStudyCentre.isPresent()) {
            return ResponseEntity.ok(new ApiResponse(null, 200, optionalStudyCentre.get()));
        } else return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> addStudyCentre(StudyCentreDto dto) {
        if (studyCentreRepository.existsByName(dto.getName())) return ALREADY_EXIST;
        StudyCentre studyCentre = new StudyCentre();
        studyCentre.setCreatedAt(new Date().getTime());
        studyCentre.setUpdatedAt(new Date().getTime());
        studyCentre.setAddress(dto.getAddress());
        studyCentre.setPhoneNumber(dto.getPhoneNumber());
        studyCentre.setName(dto.getName());
        studyCentreRepository.save(studyCentre);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> editStudyCentre(long id, StudyCentreDto dto) {
        Optional<StudyCentre> optionalStudyCentre = studyCentreRepository.findById(id);
        if (optionalStudyCentre.isPresent()) {
            StudyCentre studyCentre = optionalStudyCentre.get();
            studyCentre.setName(dto.getName());
            studyCentre.setAddress(dto.getAddress());
            studyCentre.setPhoneNumber(dto.getPhoneNumber());
            studyCentre.setUpdatedAt(new Date().getTime());
            studyCentreRepository.save(studyCentre);
            return SUCCESS;
        } else return NOT_FOUND;
    }

    @Override
    public HttpEntity<ApiResponse> deleteStudyCentre(long id) {
        try {
            studyCentreRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception exception) {
            return ResponseEntity.status(406).body(new ApiResponse(exception.getMessage(), 406, null));
        }
    }
}
