package com.rapid.swifta.RestControllers;


import com.rapid.swifta.DTOs.UserModel;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public User createUser(@RequestBody UserModel user){
        return userService.createUser(user);
    }

    @GetMapping("getallbyrandom")
    public Page<User> getAllByRandom(Pageable pageable){
        return userService.getAllUsersByRandom(pageable);
    }

    @GetMapping("getall")
    public Page<User> getAllUsers(Pageable pageable){
        return userService.getAllUsers(pageable);
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
    public Page<User> getMerchantByLocation(@RequestParam String location,
                                            Integer roleId,
                                            Pageable pageable){
        return userService.getMerchantByLocation(location, roleId, pageable);
    }

    @GetMapping("usersbylocationrandom")
    public Page<User> findAllByRandom(@RequestParam String location,
                                      @RequestParam Integer roleId, Pageable pageable){
        return userService.findAllLocationRandom(location, roleId, pageable);
    }

    @GetMapping("searchoffers")
    public Page<User> searchUser(@RequestParam String location, @RequestParam int serviceType, Pageable pageable){
        return userService.searchUsers(location, serviceType, pageable);
    }

    @PutMapping("rateUser")
    public User rateUser(@RequestParam Integer userId,
                         @RequestParam int rating){
        return userService.rateUser(userId, rating);
    }
}
