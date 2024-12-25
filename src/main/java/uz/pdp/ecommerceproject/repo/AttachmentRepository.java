package uz.pdp.ecommerceproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommerceproject.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}