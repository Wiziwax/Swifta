package com.rapid.swifta.ServicesImpl;

import com.rapid.swifta.DTOs.RequestBodies.RateRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.UserRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Address;

import com.rapid.swifta.Entities.ServiceType;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Services.UserService;
import com.rapid.swifta.Repositories.*;
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

    @Autowired
    private FavouritesRepository favouritesRepository;

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
    public UserResponse getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));
        return userResponse(user);
    }

    public User findUserByIdMethod(Integer userId){
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public User updateUser(User user) {
        User existingUser = findUserByIdMethod(user.getUserId());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMiddleName(user.getMiddleName());
        return userRepository.save(user);
    }


//    public User getMerchants(Predicate predicate) {
//        return this.userRepository.findOne(predicate).orElseThrow(()-> new ResourceNotFoundException("User not found"));
//    }

//    @Override
//    public Page<User> getMerchants(Predicate predicate, Pageable pageable) {
////
////        QUser qUser = QUser.user;
////
////        BooleanBuilder booleanBuilder = new BooleanBuilder();
////        booleanBuilder.and(qUser.role.eq(Role.fromNumericValue(3)));
////        booleanBuilder.and(qUser.firstName.eq("Wisdom"));
////
////        return userRepository.findAllByFirstNameAndLastName(booleanBuilder, pageable);
//        return null;
//    }

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
    public Page<UserResponse> findByFirstAndLastName(String firstname, String lastname, Pageable pageable) {
        Page<User> pageUser = userRepository.findAllByFirstNameContainingAndLastNameContaining(firstname, lastname, pageable);
        return userResponsesPage(pageUser);
    }


    @Override
    public User rateUser(RateRequestBody rateRequestBody) {

        User existingUser = userRepository.findById(rateRequestBody.getId()).orElseThrow(()-> new ResourceNotFoundException("User with Id " + rateRequestBody.getId() + "could not be found"));
        float massRating = existingUser.getRateCount() * existingUser.getRating();
        int newRateCount = existingUser.getRateCount() + 1;
        float newMassRating = rateRequestBody.getRating() + massRating;
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
        Integer signedInUser = 1;
        return users.map(user -> UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdDate(user.getCreatedDate())
                .jobDescription(user.getJobDescription())
                .isFavourite(favouritesRepository.existsByCreatedByAndUserFavourite(signedInUser, user.getUserId()))//TODO CHANGE IT TO SIGNED IN USER
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
                .userId(user.getUserId())
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
                .jobDescription(user.getJobDescription())
                .role(user.getRole())
                .rating(user.getRating())
                .jobCount(user.getJobCount())
                .rateCount(user.getRateCount())
                .isBusy(user.isBusy())
                .verified(user.isVerified()).build();
    }

}