package uz.o_rustamov.readium.data_loader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.o_rustamov.readium.enums.Permission;
import uz.o_rustamov.readium.role.model.Role;
import uz.o_rustamov.readium.role.repository.RoleRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Arrays;

@Component
public class MyDataLoader implements CommandLineRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;

    public MyDataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setName("ROLE_DEVELOPER");
        role.setPermissionList(Arrays.asList(Permission.values()));
        role = roleRepository.save(role);

        User user = new User();
        user.setUsername("olimjon_rustamov");
        user.setPhoneNumber("+998900123477");
        user.setFullName("Olimjon Rustamov");
        user.setRole(role);
        user.setEnabled(true);
        user.setPassword("$2a$12$cD2bclnIqmmLoPD88vcEeeGj4OehQv6tICc8xIhlub6YqRBfJonyq");
        userRepository.save(user);
    }
}
