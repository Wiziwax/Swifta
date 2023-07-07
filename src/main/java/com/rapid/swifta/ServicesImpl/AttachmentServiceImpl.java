package com.rapid.swifta.ServicesImpl;

import com.rapid.swifta.Entities.Attachment;
import com.rapid.swifta.Repositories.AttachmentRepository;
import com.rapid.swifta.Services.AttachmentService;
import com.rapid.swifta.Utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository repository;

    @Override
    public String uploadImage(MultipartFile attachment) throws IOException {

        Attachment imageData = repository.save(Attachment.builder()
                .attachmentName(attachment.getOriginalFilename())
                .attachmentType(attachment.getContentType())
                .attachmentData(ImageUtils.compressImage(attachment.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + attachment.getOriginalFilename();
        }
        return null;
    }


    @Override
    public Attachment getAttachmentById(Integer attachmentId) {
        return null;
    }

    @Override
    public List<Attachment> getAttachmentByCreatedUser(Integer userId) {
        return null;
    }

    @Override
    public List<Attachment> getAttachmentByAttachmentName(String attachmentName) {
        return null;
    }

    @Override
    public byte[] downloadImagePerson(Integer attachmentId) {
        Optional<Attachment> dbImageData = repository.findByAttachmentId(attachmentId);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getAttachmentData());
        return images;
    }
}
