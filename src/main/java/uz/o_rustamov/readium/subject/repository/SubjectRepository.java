package uz.o_rustamov.readium.subject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.readium.subject.model.Subject;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    boolean existsByName(String name);

    Page<Subject> findByStudyCentre_Id(Long studyCentre_id, Pageable pageable);
}
