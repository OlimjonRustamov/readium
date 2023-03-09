package uz.o_rustamov.readium.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.o_rustamov.readium.study_centre.repository.StudyCentreRepository;
import uz.o_rustamov.readium.user.repository.UserRepository;

@Configuration
public class AdditionalConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    UserRepository userRepository;
    StudyCentreRepository studyCentreRepository;

    public AdditionalConfig(UserRepository userRepository, StudyCentreRepository studyCentreRepository) {
        this.userRepository = userRepository;
        this.studyCentreRepository = studyCentreRepository;
    }

    @Bean
    UserBelongsToStudyCentre userBelongsToStudyCentre() {
        return new UserBelongsToStudyCentre(userRepository, studyCentreRepository);
    }
}
