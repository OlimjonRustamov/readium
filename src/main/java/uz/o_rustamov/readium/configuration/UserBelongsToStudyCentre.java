package uz.o_rustamov.readium.configuration;

import org.springframework.stereotype.Component;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.study_centre.repository.StudyCentreRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Optional;

@Component
public class UserBelongsToStudyCentre {
    UserRepository userRepository;

    StudyCentreRepository studyCentreRepository;

    public UserBelongsToStudyCentre(UserRepository userRepository, StudyCentreRepository studyCentreRepository) {
        this.userRepository = userRepository;
        this.studyCentreRepository = studyCentreRepository;
    }

    public boolean check(long userId, long studyCentreId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) return false;
        User user = optionalUser.get();

        Optional<StudyCentre> optionalStudyCentre = studyCentreRepository.findById(studyCentreId);
        if (!optionalStudyCentre.isPresent()) return false;
        StudyCentre studyCentre = optionalStudyCentre.get();

//        if(user)
        return false;
    }
}
