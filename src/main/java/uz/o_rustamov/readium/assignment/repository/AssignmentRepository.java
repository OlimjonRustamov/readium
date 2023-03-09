package uz.o_rustamov.readium.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.readium.assignment.model.Assignment;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findAllByGroup_Id(Long group_id);

    List<Assignment> findAllByGroup_IdAndActive(Long group_id, boolean active);

}
