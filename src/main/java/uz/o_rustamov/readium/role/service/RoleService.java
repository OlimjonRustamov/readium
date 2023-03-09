package uz.o_rustamov.readium.role.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.role.model.RoleDto;

public interface RoleService {

    HttpEntity<ApiResponse> getRoles();

    HttpEntity<ApiResponse> addRole(RoleDto dto);

    HttpEntity<ApiResponse> deleteRole(long id);

    HttpEntity<ApiResponse> getRoleById(long id);
}
