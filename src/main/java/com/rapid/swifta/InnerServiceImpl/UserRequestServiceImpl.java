package com.rapid.swifta.InnerServiceImpl;

import com.rapid.swifta.Entities.UserRequest;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Repositories.UserRequestRepository;
import com.rapid.swifta.InnerService.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserRequestServiceImpl implements UserRequestService {

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Override
    public Page<UserRequest> getAllRequests(Pageable pageable) {
        return userRequestRepository.findAll(pageable);
    }

    @Override
    public UserRequest createUserRequest(UserRequest userRequest) {
        return userRequestRepository.save(userRequest);
    }

    @Override
    public void deleteUserRequest(Integer userRequestId) {
        userRequestRepository.deleteById(userRequestId);
    }



    @Override
    public UserRequest getUserRequestById(Integer userRequestId) {
        return userRequestRepository.findById(userRequestId).
                orElseThrow(()-> new ResourceNotFoundException("Request with ID " + userRequestId + " was not found"));
    }


}
