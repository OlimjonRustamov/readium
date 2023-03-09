package uz.o_rustamov.readium.role.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.role.model.Role;
import uz.o_rustamov.readium.role.model.RoleDto;
import uz.o_rustamov.readium.role.repository.RoleRepository;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.*;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getRoles() {
        return ResponseEntity.ok(new ApiResponse(
                null, 200, roleRepository.findAll()));
    }

    @Override
    public HttpEntity<ApiResponse> addRole(RoleDto dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setPermissionList(dto.getPermissionList());
        try {
            role = roleRepository.save(role);
            return SUCCESS;
        } catch (Exception e) {
            return ALREADY_EXIST;
        }
    }

    @Override
    public HttpEntity<ApiResponse> deleteRole(long id) {
        try {
            roleRepository.deleteById(id);
            return SUCCESS;
        } catch (DataIntegrityViolationException ex) {
            return CONSTRAINT_EXCEPTION;
        } catch (Exception e) {
            return NOT_FOUND;
        }
    }

    @Override
    public HttpEntity<ApiResponse> getRoleById(long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            return ResponseEntity.ok(new ApiResponse(null, 200, optionalRole.get()));
        }
        return NOT_FOUND;
    }
}
