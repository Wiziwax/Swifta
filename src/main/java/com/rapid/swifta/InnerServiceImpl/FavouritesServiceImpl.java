package com.rapid.swifta.InnerServiceImpl;

import com.rapid.swifta.DTOs.RequestBodies.FavouritesRequestBody;
import com.rapid.swifta.DTOs.Responses.UserResponse;
import com.rapid.swifta.Entities.Favourites;
import com.rapid.swifta.Entities.ServiceType;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Repositories.FavouritesRepository;
import com.rapid.swifta.Repositories.UserRepository;
import com.rapid.swifta.InnerService.FavouritesService;
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
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

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
        return userService.userResponsesPage(userFavouriteList);

    }

    @Override
    public boolean isFavourite(Integer userId, Integer favouriteId) {
        return false;
    }

    public List<String> serviceTypeList(List<ServiceType> serviceTypes){
        List<String> stringList = new ArrayList<>();

        for (ServiceType serviceType : serviceTypes) {
            stringList.add(serviceType.getServiceTypeName());
        }
        return stringList;
    }
}
