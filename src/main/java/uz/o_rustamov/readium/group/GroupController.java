package uz.o_rustamov.readium.group;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.group.dto.GroupDto;
import uz.o_rustamov.readium.group.service.GroupServiceImpl;
import uz.o_rustamov.readium.user.model.User;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/group")
public class GroupController {

    GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @PreAuthorize("hasAuthority('VIEW_GROUPS')")
    @GetMapping()
    public HttpEntity<?> getStudyCentreGroups(@CurrentUser User user, @RequestParam("page") int page, @RequestParam("size") int size) {
        return groupService.getAllGroupsOfStudyCentre(user, page, size);
    }

    @PreAuthorize("hasAuthority('VIEW_GROUPS')")
    @GetMapping("/{groupId}")
    public HttpEntity<?> getSingleGroup(@PathVariable long groupId) {
        return groupService.getSingleGroup(groupId);
    }

    @PreAuthorize("hasAuthority('ADD_GROUP')")
    @PostMapping()
    public HttpEntity<?> addGroup(@CurrentUser User user, @Valid @RequestBody GroupDto dto) {
        return groupService.addGroup(user, dto);
    }

    @PreAuthorize("hasAuthority('EDIT_GROUP')")
    @PutMapping("/{id}")
    public HttpEntity<?> editGroup(@CurrentUser User user, @PathVariable long id, @Valid @RequestBody GroupDto dto) {
        return groupService.editGroup(user, dto, id);
    }

    @PreAuthorize("hasAuthority('EDIT_GROUP')")
    @PutMapping("/activate")
    public HttpEntity<?> activateGroup(@CurrentUser User user, @RequestParam("group_id") long groupId, @RequestParam("is_active") boolean isActive) {
        return groupService.activateGroup(user, groupId, isActive);
    }

    @PreAuthorize("hasAuthority('DELETE_GROUP')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteGroup(@CurrentUser User user, @PathVariable long id) {
        return groupService.deleteGroup(user, id);
    }

}
