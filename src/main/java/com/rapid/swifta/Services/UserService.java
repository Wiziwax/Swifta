package com.rapid.swifta.Services;


import com.rapid.swifta.DTOs.RequestBodies.UserRequestBody;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getAllUsersByRandom(Pageable pageable);

    User createUser(UserRequestBody user);
    Page<Address> findAllByRandomAndByLocation(String query, Pageable pageable);
    Page<User> getMerchantByLocation(String location,Integer roleId, Pageable pageable);
    Page<User> getAllUsers(Pageable pageable);
    Page<User> findAllLocationRandom(String digits, Integer roleId, Pageable pageable);
    void deleteUserById(Integer userId, Pageable pageable);
    User getUserById(Integer userId);
    User updateUser(User user);
    Page<User> searchUsers(String location, int serviceType, Pageable pageable);
    Page<User>findByFirstAndLastName(String firstName, String lastName, Pageable pageable);
    User rateUser(Integer userId, int rating);

}
