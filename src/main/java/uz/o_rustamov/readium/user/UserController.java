package uz.o_rustamov.readium.user;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.user.model.UserDto;
import uz.o_rustamov.readium.user.service.UserServiceImpl;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/user")
public class UserController {

    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    //pagination added
    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping
    public HttpEntity<ApiResponse> getUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.getUsers(page, size);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getUserById(@PathVariable long id) {
        return userService.getUser(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping
    public HttpEntity<ApiResponse> addUser(@Valid @RequestBody UserDto dto) {
        return userService.addUser(dto);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editUser(@Valid @RequestBody UserDto dto, @PathVariable long id) {
        return userService.editUser(id, dto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
