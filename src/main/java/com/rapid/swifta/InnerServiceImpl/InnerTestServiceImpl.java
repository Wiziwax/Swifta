package com.rapid.swifta.InnerServiceImpl;

import com.querydsl.core.types.Predicate;
import com.rapid.swifta.DTOs.RequestBodies.UserRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.InnerService.UserService;
import com.rapid.swifta.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InnerTestServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public  InnerTestServiceImpl(){

    }

    public Page<UserResponse> getAllUsersByRandom(Pageable pageable) {
        return null;
    }

    public User createUser(UserRequestBody user) {
        return null;
    }

    public Page<Address> findAllByRandomAndByLocation(String query, Pageable pageable) {
        return null;
    }

    public Page<UserResponse> getMerchantByLocation(String location, Integer roleId, Pageable pageable) {
        return null;
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return null;
    }

    public Page<UserResponse> findAllLocationRandom(String digits, Integer roleId, Pageable pageable) {
        return null;
    }

    public void deleteUserById(Integer userId, Pageable pageable) {

    }

    public UserResponse getUserById(Integer userId) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public Page<User> getMerchants(Predicate predicate, Pageable pageable) {
        return null;
    }

    public Page<UserResponse> searchUsers(String location, int serviceType, Pageable pageable) {
        return null;
    }

    public Page<User> findByFirstAndLastName(Predicate predicate, Pageable pageable) {
        return this.userRepository.findAllByFirstNameAndLastName(predicate, pageable);
    }

    public User rateUser(Integer userId, int rating) {
        return null;
    }
}
