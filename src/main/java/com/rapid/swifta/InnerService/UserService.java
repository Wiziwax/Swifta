package com.rapid.swifta.InnerService;


import com.querydsl.core.types.Predicate;
import com.rapid.swifta.DTOs.RequestBodies.UserRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
    Page<User> getMerchants(Predicate predicate, Pageable pageable);
    Page<UserResponse> searchUsers(String location, int serviceType, Pageable pageable);
    Page<User>findByFirstAndLastName(Predicate predicate, Pageable pageable);
    User rateUser(Integer userId, int rating);

}
