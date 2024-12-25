package uz.pdp.ecommerceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerceproject.entity.Attachment;
import uz.pdp.ecommerceproject.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Integer> {
    Optional<AttachmentContent> findByAttachment(Attachment attachment);
}