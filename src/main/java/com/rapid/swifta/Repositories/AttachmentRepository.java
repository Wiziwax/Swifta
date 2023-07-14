package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.Attachment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    Optional<Attachment> findByAttachmentId(Integer attachmentId);
    List<Attachment> findAllByCreatedBy(Integer userId);
    List<Attachment> findByAttachmentName(String name);
    List<Attachment> findByServiceId(Integer serviceId);
    Optional<Attachment> findByCreatedBy(Integer userId);
}
