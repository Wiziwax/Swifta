package com.rapid.swifta.RestControllers;


import com.rapid.swifta.DTOs.RequestBodies.RateRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.UserRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

//    User sign in
    @PostMapping("create")
    public User createUser(@RequestBody UserRequestBody user){
        return userService.createUser(user);
    }

//    Get all users by random
    @GetMapping("getallbyrandom")
    public Page<UserResponse> getAllByRandom(Pageable pageable){
        return userService.getAllUsersByRandom(pageable);
    }

//    Get all users
    @GetMapping("getall")
    public Page<UserResponse> getAllUsers(Pageable pageable){
        return userService.getAllUsers(pageable);
    }

//    Find user by username
    @GetMapping("getbyfirstandlastname")
    public Page<UserResponse> getByFirstAndLastName(@RequestParam String firstName,
                                      @RequestParam String lastName,
                                      Pageable pageable){
        return userService.findByFirstAndLastName(firstName, lastName, pageable);
    }

//    Delete User
    @DeleteMapping("delete")
    public void deleteUser(@RequestBody Integer userId, Pageable pageable){
        userService.deleteUserById(userId, pageable);
    }


    @GetMapping("findrandombylocation")
    public Page<Address> randomLocation(@RequestParam String query, Pageable pageable){
        return userService.findAllByRandomAndByLocation(query, pageable);
    }

//    Get user by id
    @GetMapping("getuserbyid")
    public UserResponse getUserById(@RequestParam Integer userId){
        return userService.getUserById(userId);
    }

//    Update User information
    @PutMapping("updateuser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

//    Get users by location and role(ADMIN, USER, CLIENT, etc.)
    @GetMapping("getmerchantsbylocationandroleid")
    public Page<UserResponse> getMerchantByLocation(@RequestParam(required = false) String location,
                                            @RequestParam(required = false) Integer roleId,
                                            Pageable pageable){
        return userService.getMerchantByLocation(location, roleId, pageable);
    }

//    Get Merchants by location
    @GetMapping("getmerchantsbylocation")
    public Page<UserResponse> getMerchantByLocation(@RequestParam(required = false) String location,
                                                    Pageable pageable){
        return userService.getMerchantByLocation(location, 1, pageable);
    }

//    Get users by location
    @GetMapping("usersbylocationrandom")
    public Page<UserResponse> findAllByRandom(@RequestParam(required = false) String location,
                                                @RequestParam Integer roleId, Pageable pageable){
        return userService.findAllLocationRandom(location, roleId, pageable);
    }

//    Search user by location and service type
    @GetMapping("searchoffers")
    public Page<UserResponse> searchUser(@RequestParam(required = false) String location,
                                         @RequestParam (required = false) Integer serviceType, Pageable pageable){
        return userService.searchUsers(location, serviceType, pageable);
    }

//    Rate User
    @PutMapping("rateUser")
    public User rateUser(@RequestBody RateRequestBody rateRequestBody){
        return userService.rateUser(rateRequestBody);
    }



}
