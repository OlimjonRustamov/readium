package uz.o_rustamov.readium.study_centre.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.study_centre.model.StudyCentreDto;
import uz.o_rustamov.readium.user.model.UserDto;

public interface StudyCentreService {

    HttpEntity<ApiResponse> getAllStudyCentres(int page, int size);

    HttpEntity<ApiResponse> getSingleStudyCentre(long id);

    HttpEntity<ApiResponse> addStudyCentre(StudyCentreDto dto);

    HttpEntity<ApiResponse> editStudyCentre(long id, StudyCentreDto dto);

    HttpEntity<ApiResponse> deleteStudyCentre(long id);

}
