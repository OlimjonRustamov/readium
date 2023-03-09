package uz.o_rustamov.readium.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.o_rustamov.readium.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);
}
