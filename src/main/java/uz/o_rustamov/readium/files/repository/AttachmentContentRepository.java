package uz.o_rustamov.readium.files.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.o_rustamov.readium.files.model.AttachmentContent;

import java.util.Optional;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {

    Optional<AttachmentContent> findByAttachment_Id(long attachment_id);

}
