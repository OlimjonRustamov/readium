package uz.o_rustamov.readium.subject.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.subject.dto.SubjectDto;
import uz.o_rustamov.readium.user.model.User;

public interface SubjectService {

    HttpEntity<ApiResponse> getSubjectsOfStudyCentre(User user);

    HttpEntity<ApiResponse> getSingleSubject(User user, long id);

    HttpEntity<ApiResponse> addSubject(User user, SubjectDto dto);

    HttpEntity<ApiResponse> editSubject(long id, SubjectDto dto);

    HttpEntity<ApiResponse> deleteSubject(long id);
}
