package com.rapid.swifta.ServiceImpl;

import com.rapid.swifta.DTOs.RequestBodies.FavouritesRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.Entities.ServiceType;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Repositories.FavouritesRepository;
import com.rapid.swifta.Repositories.ServiceTypeRepository;
import com.rapid.swifta.Repositories.UserRepository;
import com.rapid.swifta.Services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private FavouritesRepository favouritesRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Favourites createFavourites(FavouritesRequestBody favourites) {
        Favourites newFavourite = new Favourites();
        newFavourite.setCreatedBy(favourites.getCreatedBy());
        newFavourite.setUserFavourite(favourites.getUserFavourite());
        if (favouritesRepository.existsByCreatedByAndUserFavourite(favourites.getCreatedBy(), favourites.getUserFavourite())){

           favouritesRepository.deleteAll(favouritesRepository.findAllByCreatedByAndUserFavourite(favourites.getCreatedBy(),
                   favourites.getUserFavourite()));
        }



        else {
            return favouritesRepository.save(newFavourite);
        }
        return null;
    }

    @Override
    public void deleteFavouritesById(Favourites favourites) {
        favouritesRepository.deleteAll(favouritesRepository.findAllByCreatedByAndUserFavourite(favourites.getCreatedBy(),
                favourites.getUserFavourite()));
    }

    @Override
    public Page<UserResponse> getAllFavourites(Integer userId, Pageable pageable) {
        Page<Favourites> favourites =favouritesRepository.getAllByCreatedBy(userId, pageable);
        List<Integer> favouritesLists = new ArrayList<>();

        for(Favourites favourites1: favourites){
            favouritesLists.add(favourites1.getUserFavourite());
        }

        Page<User> userFavouriteList =  userRepository.findAll(favouritesLists, pageable);
        return userFavouriteList.map(user -> UserResponse.builder()
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .username(user.getUsername())
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
                .build());
    }

    public List<String> serviceTypeList(List<ServiceType> serviceTypes){
        List<String> stringList = new ArrayList<>();

        for (ServiceType serviceType : serviceTypes) {
            stringList.add(serviceType.getServiceTypeName());
        }
        return stringList;
    }
}
