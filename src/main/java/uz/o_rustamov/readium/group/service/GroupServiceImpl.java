package uz.o_rustamov.readium.group.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.group.dto.GroupDto;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.group.repository.GroupRepository;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.study_centre.repository.StudyCentreRepository;
import uz.o_rustamov.readium.subject.model.Subject;
import uz.o_rustamov.readium.subject.repository.SubjectRepository;
import uz.o_rustamov.readium.user.model.User;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class GroupServiceImpl implements GroupService {

    StudyCentreRepository studyCentreRepository;
    SubjectRepository subjectRepository;
    GroupRepository groupRepository;

    public GroupServiceImpl(StudyCentreRepository studyCentreRepository, GroupRepository groupRepository, SubjectRepository subjectRepository) {
        this.studyCentreRepository = studyCentreRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getAllGroupsOfStudyCentre(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, groupRepository.findByStudyCentre_Id(user.getStudyCentre().getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getSingleGroup(long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (!optionalGroup.isPresent()) return NOT_FOUND;
        return ResponseEntity.ok(new ApiResponse(null, 200, optionalGroup.get()));
    }

    @Override
    public HttpEntity<ApiResponse> addGroup(User user, GroupDto dto) {
        Optional<Subject> optionalSubject = subjectRepository.findById(dto.getSubjectId());
        if (!optionalSubject.isPresent()) {
            return NOT_FOUND;
        }
        Optional<StudyCentre> optionalStudyCentre = studyCentreRepository.findById(dto.getStudyCentreId());
        if (!optionalStudyCentre.isPresent()) {
            return NOT_FOUND;
        }

        Group group = new Group();
        group.setName(dto.getName());
        group.setTeacherName(dto.getTeacherName());
        group.setSubject(optionalSubject.get());
        group.setStudyCentre(optionalStudyCentre.get());
        groupRepository.save(group);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> editGroup(User user, GroupDto dto, long id) {
        return ON_PROCESS;
    }

    @Override
    public HttpEntity<ApiResponse> activateGroup(User user, long groupId, boolean isActive) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (!optionalGroup.isPresent()) return NOT_FOUND;
        Group group = optionalGroup.get();
        group.setActive(isActive);
        groupRepository.save(group);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deleteGroup(User user, long groupId) {
        try {
            groupRepository.deleteById(groupId);
            return SUCCESS;
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new ApiResponse(ex.getMessage(), 400, null));
        }
    }
}
