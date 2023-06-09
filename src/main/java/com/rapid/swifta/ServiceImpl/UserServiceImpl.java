package com.rapid.swifta.ServiceImpl;


import com.rapid.swifta.DTOs.RequestBodies.UserRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.ServiceType;
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
    public Page<UserResponse> getAllUsersByRandom(Pageable pageable) {
        Page<User> users = userRepository.findAllByRandom(pageable);
        return userResponsesPage(users);
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
    public Page<UserResponse> getMerchantByLocation(String location, Integer roleId, Pageable pageable) {
        List<Integer> digits = findByLocation(location);
        Role role = Role.fromNumericValue(roleId);
        String roleName = role.name();
        Page<User> users = userRepository.findMerchantsByLocation(digits, roleName, pageable);
        return userResponsesPage(users);
    }

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        Page<User> users =userRepository.findAll(pageable);
        return userResponsesPage(users);
    }

    @Override
    public Page<UserResponse> findAllLocationRandom(String location, Integer roleId, Pageable pageable) {
        List<Integer> digits = findByLocation(location);
        Role role = Role.fromNumericValue(roleId);
        String roleName = role.name();
        Page<User> users = userRepository.findAllLocationRandom(digits, roleName, pageable);
        return userResponsesPage(users);
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
    public Page<UserResponse> searchUsers(String location, int serviceType, Pageable pageable) {
        List<Integer> addressIds = findByLocation(location);
        Page<User> users = userRepository.findByServiceTypeAndLocation(addressIds, serviceType, pageable);
        return userResponsesPage(users);
    }

    @Override
    public Page<UserResponse> findByFirstAndLastName(String firstName, String lastName, Pageable pageable) {
        Page<User> userPage= userRepository.findAllByFirstNameContainingAndLastNameContaining(firstName, lastName, pageable);
        return userResponsesPage(userPage);
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

    public List<String> serviceTypeList(List<ServiceType> serviceTypes){
        List<String> stringList = new ArrayList<>();

        for (ServiceType serviceType : serviceTypes) {
            stringList.add(serviceType.getServiceTypeName());
        }
        return stringList;
    }

    public Page<UserResponse> userResponsesPage(Page<User> users){
        return users.map(user -> UserResponse.builder()
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .mobile(user.getMobile())
                .image(user.getImage())
                .address(user.getAddress().getHouseNumber()+ ", "+
                        user.getAddress().getStreetName()+", "+
                        user.getAddress().getArea()+", "+
                        user.getAddress().getState()+", "+
                        user.getAddress().getCountry())
                .serviceType(serviceTypeList(user.getServiceType()))
                .role(user.getRole())
                .rating(user.getRating())
                .isBusy(user.isBusy())
                .rateCount(user.getRateCount()).build());
    }

    public UserResponse userResponse(User user){
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .mobile(user.getMobile())
                .image(user.getImage())
                .address(user.getAddress().getHouseNumber()+ ", "+
                        user.getAddress().getStreetName()+", "+
                        user.getAddress().getArea()+", "+
                        user.getAddress().getState()+", "+
                        user.getAddress().getCountry())
                .serviceType(serviceTypeList(user.getServiceType()))
                .role(user.getRole())
                .rating(user.getRating())
                .jobCount(user.getJobCount())
                .rateCount(user.getRateCount())
                .isBusy(user.isBusy())
                .verified(user.isVerified()).build();
    }
}
