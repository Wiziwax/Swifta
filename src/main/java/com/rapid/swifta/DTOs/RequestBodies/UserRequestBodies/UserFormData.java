package com.rapid.swifta.DTOs.RequestBodies.UserRequestBodies;

import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserFormData {

    private String userRequest;

    @Nullable
    private MultipartFile userImage;
}
