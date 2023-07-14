package com.rapid.swifta.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rapid.swifta.DTOs.RequestBodies.RateRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.UserRequestBodies.UserFormData;
import com.rapid.swifta.DTOs.RequestBodies.UserRequestBodies.UserRequestBody;
import com.rapid.swifta.DTOs.Responses.UserImageResponse;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;


public interface UserService {

    Page<UserResponse> getAllUsersByRandom(Pageable pageable);
    User createUser(UserRequestBody user);
    Page<Address> findAllByRandomAndByLocation(String query, Pageable pageable);
    Page<UserResponse> getMerchantByLocation(String location,Integer roleId, Pageable pageable);
    Page<UserResponse> getAllUsers(Pageable pageable);
    Page<UserResponse> findAllLocationRandom(String digits, Integer roleId, Pageable pageable);
    void deleteUserById(Integer userId, Pageable pageable);
    UserResponse getUserById(Integer userId);
    User updateUser(User user);
//    User getMerchants(Predicate predicate);
    Page<UserResponse> searchUsers(String location, int serviceType, Pageable pageable);
    Page<UserResponse>findByFirstAndLastName(String firstname, String lastname, Pageable pageable);
    User rateUser(RateRequestBody rateRequestBody);
//    Page<UserImageResponse> userAndImage(Pageable pageable);


    User createUserwithImage(UserFormData userRequestDTO) throws IOException;

    Page<UserImageResponse> getUsersWithImage(Pageable pageable);

    UserImageResponse getUserWithImage(Integer userId);
}
