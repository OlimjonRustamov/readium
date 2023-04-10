package uz.o_rustamov.readium.role;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.role.model.RoleDto;
import uz.o_rustamov.readium.role.service.RoleServiceImpl;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController
@RequestMapping("/api/role")
public class RoleController {

    RoleServiceImpl roleService;

    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto dto) {
        return roleService.addRole(dto);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLES')")
    @GetMapping
    public HttpEntity<?> viewRoles() {
        return roleService.getRoles();
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLES')")
    @GetMapping("/{id}")
    public HttpEntity<?> viewRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping(value = "{id}")
    public HttpEntity<?> deleteRole(@PathVariable long id) {
        return roleService.deleteRole(id);
    }
}
