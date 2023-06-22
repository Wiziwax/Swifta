package com.rapid.swifta.RestControllers;

import com.rapid.swifta.Entities.UserRequest;
import com.rapid.swifta.Services.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/request")
public class UserRequestRestController {

    @Autowired
    private UserRequestService userRequestService;

    @GetMapping("getall")
    public Page<UserRequest> getAllRequests(Pageable pageable){
        return userRequestService.getAllRequests(pageable);
    }

    @GetMapping("getbyid")
    public UserRequest getUserRequestById(@RequestParam Integer requestId){
        return userRequestService.getUserRequestById(requestId);
    }

    @PostMapping("create")
    public UserRequest createUserRequest(@RequestBody UserRequest userRequest){
        return userRequestService.createUserRequest(userRequest);
    }


}
