package com.rapid.swifta.Services;

import com.rapid.swifta.Entities.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRequestService {

    Page<UserRequest> getAllRequests(Pageable pageable);
    UserRequest createUserRequest(UserRequest userRequest);
    void deleteUserRequest(Integer userRequestId);
    UserRequest getUserRequestById(Integer userRequestId);

}
