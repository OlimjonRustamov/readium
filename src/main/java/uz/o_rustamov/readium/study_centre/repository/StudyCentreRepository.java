package uz.o_rustamov.readium.study_centre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.readium.news_events.model.News;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;

import java.util.List;

@Repository
public interface StudyCentreRepository extends JpaRepository<StudyCentre, Long> {

    boolean existsByName(String name);

}
