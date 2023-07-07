package com.rapid.swifta.Services;

import com.rapid.swifta.Entities.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {

    String uploadImage(MultipartFile attachment) throws IOException;

    Attachment getAttachmentById(Integer attachmentId);
    List<Attachment> getAttachmentByCreatedUser(Integer userId);
    List<Attachment> getAttachmentByAttachmentName(String attachmentName);
    byte[] downloadImagePerson(Integer attachmentId);



}
