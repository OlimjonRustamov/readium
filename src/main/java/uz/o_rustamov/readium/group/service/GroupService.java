package uz.o_rustamov.readium.group.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.group.dto.GroupDto;
import uz.o_rustamov.readium.user.model.User;

public interface GroupService {

    HttpEntity<ApiResponse> getAllGroupsOfStudyCentre(User user);

    HttpEntity<ApiResponse> getSingleGroup(long groupId);

    HttpEntity<ApiResponse> addGroup(User user, GroupDto dto);

    HttpEntity<ApiResponse> editGroup(User user, GroupDto dto, long id);

    HttpEntity<ApiResponse> activateGroup(User user, long groupId, boolean isActive);

    HttpEntity<ApiResponse> deleteGroup(User user, long groupId);
}
