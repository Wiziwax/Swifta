package com.rapid.swifta.RestControllers;


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

    @PostMapping("create")
    public User createUser(@RequestBody UserRequestBody user){
        return userService.createUser(user);
    }

    @GetMapping("getallbyrandom")
    public Page<UserResponse> getAllByRandom(Pageable pageable){
        return userService.getAllUsersByRandom(pageable);
    }

    @GetMapping("getall")
    public Page<UserResponse> getAllUsers(Pageable pageable){
        return userService.getAllUsers(pageable);
    }

    @GetMapping("getbyfirstandlastname")
    public Page<UserResponse> getByFirstAndLastName(@RequestBody User user, Pageable pageable){
        return userService.findByFirstAndLastName(user.getFirstName(), user.getLastName(), pageable);
    }

    @DeleteMapping("delete")
    public void deleteUser(@RequestBody Integer userId, Pageable pageable){
        userService.deleteUserById(userId, pageable);
    }

    @GetMapping("findrandombylocation")
    public Page<Address> randomLocation(@RequestParam String query, Pageable pageable){
        return userService.findAllByRandomAndByLocation(query, pageable);
    }

    @GetMapping("getuserbyid")
    public User getUserById(@RequestParam Integer userId){
        return userService.getUserById(userId);
    }

    @PutMapping("updateuser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("getusersbylocationandroleid")
    public Page<UserResponse> getMerchantByLocation(@RequestParam String location,
                                            Integer roleId,
                                            Pageable pageable){
        return userService.getMerchantByLocation(location, roleId, pageable);
    }

    @GetMapping("usersbylocationrandom")
    public Page<UserResponse> findAllByRandom(@RequestParam String location,
                                      @RequestParam Integer roleId, Pageable pageable){
        return userService.findAllLocationRandom(location, roleId, pageable);
    }

    @GetMapping("searchoffers")
    public Page<UserResponse> searchUser(@RequestParam String location, @RequestParam int serviceType, Pageable pageable){
        return userService.searchUsers(location, serviceType, pageable);
    }

    @PutMapping("rateUser")
    public User rateUser(@RequestParam Integer userId,
                         @RequestParam int rating){
        return userService.rateUser(userId, rating);
    }

}
