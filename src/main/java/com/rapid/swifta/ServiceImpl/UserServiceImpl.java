package com.rapid.swifta.ServiceImpl;


import com.rapid.swifta.DTOs.RequestBodies.UserRequestBody;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Repositories.AddressRepository;
import com.rapid.swifta.Repositories.ServiceTypeRepository;
import com.rapid.swifta.Repositories.UserRepository;
import com.rapid.swifta.Services.UserService;
import com.rapid.swifta.UserProps.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Override
    public Page<User> getAllUsersByRandom(Pageable pageable) {
        return userRepository.findAllByRandom(pageable);
    }

    @Override
    public User createUser(UserRequestBody user) {

        user.setRole(Role.ADMIN);
        User newUser = new User();
        mapFields(user, newUser);
        newUser.setRole(Role.ADMIN);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public Page<Address> findAllByRandomAndByLocation(String query, Pageable pageable) {
        return addressRepository.findAllByRandomAndLocation(query, pageable);
    }

    @Override
    public Page<User> getMerchantByLocation(String location, Integer roleId, Pageable pageable) {
        List<Integer> digits = findByLocation(location);
        Role role = Role.fromNumericValue(roleId);
        String roleName = role.name();
        return userRepository.findMerchantsByLocation(digits, roleName, pageable);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllLocationRandom(String location, Integer roleId, Pageable pageable) {
        List<Integer> digits = findByLocation(location);
        Role role = Role.fromNumericValue(roleId);
        String roleName = role.name();
        return userRepository.findAllLocationRandom(digits, roleName, pageable);
    }

    @Override
    public void deleteUserById(Integer userId, Pageable pageable) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public User updateUser(User user) {
        User existingUser = getUserById(user.getUserId());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMiddleName(user.getMiddleName());
        return userRepository.save(user);
    }

    public List<Integer> findByLocation(String location) {
        List <Address> addressList = addressRepository.findByStreetNameContainingOrAreaContainingOrStateContainingOrCountryContaining(location, location, location, location);
        List<Integer> addressIds = new ArrayList<>();
        for(Address address: addressList){
            Integer addressId = address.getAddressId();
            addressIds.add(addressId);
        }
        return addressIds;
    }

    @Override
    public Page<User> searchUsers(String location, int serviceType, Pageable pageable) {
        List<Integer> addressIds = findByLocation(location);
        return userRepository.findByServiceTypeAndLocation(addressIds, serviceType, pageable);
    }

    @Override
    public Page<User> findByFirstAndLastName(String firstName, String lastName, Pageable pageable) {
        return userRepository.findAllByFirstNameContainingAndLastNameContaining(firstName, lastName, pageable);
    }

    @Override
    public User rateUser(Integer userId, int rating) {

        User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with Id " + userId + "could not be found"));
        float massRating = existingUser.getRateCount() * existingUser.getRating();
        int newRateCount = existingUser.getRateCount() + 1;
        float newMassRating = rating + massRating;
        float newRating = newMassRating / newRateCount;
        existingUser.setRating(newRating);
        existingUser.setRateCount(newRateCount);
        return userRepository.save(existingUser);
    }


    private void mapFields(UserRequestBody user, User newUser) {
        newUser.setFirstName(user.getFirstName());
        newUser.setMiddleName(user.getMiddleName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setMobile(user.getMobile());
        newUser.setAddress(user.getAddress());
        newUser.setServiceType(serviceTypeRepository.findAllById(user.getServiceType()));

    }
}
